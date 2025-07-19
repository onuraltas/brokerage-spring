package com.brokerage.spring.service.impl;

import com.brokerage.spring.dto.OrderDto;
import com.brokerage.spring.dto.request.CreateOrderRequestDto;
import com.brokerage.spring.dto.response.CreateOrderResponseDto;
import com.brokerage.spring.entity.AssetEntity;
import com.brokerage.spring.entity.OrderEntity;
import com.brokerage.spring.enums.ExceptionMessage;
import com.brokerage.spring.enums.Side;
import com.brokerage.spring.enums.Status;
import com.brokerage.spring.exceptions.BrokerageException;
import com.brokerage.spring.mapper.OrderMapper;
import com.brokerage.spring.repository.AssetRepository;
import com.brokerage.spring.repository.OrderRepository;
import com.brokerage.spring.service.OrderService;
import com.brokerage.spring.util.AssetUtil;
import com.brokerage.spring.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssetRepository assetRepository;
    private final OrderMapper orderMapper;
    private final WebUtils webUtils;

    @Override
    public CreateOrderResponseDto createBuyOrder(CreateOrderRequestDto requestDto) {
        var createdOrderEntity = createOrder(requestDto.getAssetName(), Side.BUY, requestDto.getSize(), requestDto.getPrice());

        var orderDto = orderMapper.toDto(createdOrderEntity);

        return new CreateOrderResponseDto(orderDto);
    }

    @Override
    public CreateOrderResponseDto createSellOrder(CreateOrderRequestDto requestDto) {
        var createdOrderEntity = createOrder(requestDto.getAssetName(), Side.SELL, requestDto.getSize(), requestDto.getPrice());

        var orderDto = orderMapper.toDto(createdOrderEntity);

        return new CreateOrderResponseDto(orderDto);
    }

    private OrderEntity createOrder(String assetName, Side orderSide, BigDecimal size, BigDecimal price) {

        if(assetName.equals(AssetUtil.TRY)) {
            throw new BrokerageException(ExceptionMessage.TRY_ASSET_CANNOT_BE_TRADED);
        }

        if(!AssetUtil.isValidAsset(assetName)) {
            throw new BrokerageException(ExceptionMessage.UNKNOWN_ASSET, assetName);
        }

        var newOrderEntity = new OrderEntity();
        newOrderEntity.setCustomerId(webUtils.getLoginnedCustomerId());
        newOrderEntity.setStatus(Status.PENDING);
        newOrderEntity.setCreateDate(LocalDateTime.now());
        newOrderEntity.setAssetName(assetName);
        newOrderEntity.setOrderSide(orderSide);
        newOrderEntity.setSize(size);
        newOrderEntity.setPrice(price);

        if(Side.SELL.equals(orderSide)) {
            var existingAssetEntity = assetRepository
                            .findByCustomerIdAndAssetName(newOrderEntity.getCustomerId(), newOrderEntity.getAssetName())
                            .orElseThrow(() -> new BrokerageException(ExceptionMessage.ASSET_NOT_FOUND));

            var existingTryAssetEntity = assetRepository
                    .findByCustomerIdAndAssetName(newOrderEntity.getCustomerId(), AssetUtil.TRY)
                    .orElseThrow(() -> new BrokerageException(ExceptionMessage.PARAM_ASSET_NOT_FOUND, AssetUtil.TRY));

            if(existingAssetEntity.getUsableSize().subtract(size).compareTo(BigDecimal.ZERO) < 0) {
                throw new BrokerageException(ExceptionMessage.ASSET_NOT_ENOUGH);
            }

            var matchedBuyOrderEntityList = orderRepository
                    .findMatchedOrder(Side.BUY, newOrderEntity.getAssetName(), size, price, newOrderEntity.getCustomerId());

            var matchedBuyOrderEntity = !matchedBuyOrderEntityList.isEmpty() ? matchedBuyOrderEntityList.getFirst() : null;

            if(matchedBuyOrderEntity != null) {
                var buyerTryAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(matchedBuyOrderEntity.getCustomerId(), AssetUtil.TRY)
                        .orElseThrow(() -> new BrokerageException(ExceptionMessage.PARAM_ASSET_NOT_FOUND, AssetUtil.TRY));

                var buyerAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(matchedBuyOrderEntity.getCustomerId(), matchedBuyOrderEntity.getAssetName())
                        .orElse(null);

                if(buyerAssetEntity == null) {
                    buyerAssetEntity = new AssetEntity();
                    buyerAssetEntity.setCustomerId(matchedBuyOrderEntity.getCustomerId());
                    buyerAssetEntity.setAssetName(matchedBuyOrderEntity.getAssetName());
                    buyerAssetEntity.setSize(BigDecimal.ZERO);
                    buyerAssetEntity.setUsableSize(BigDecimal.ZERO);
                }

                newOrderEntity.setStatus(Status.MATCHED);
                matchedBuyOrderEntity.setStatus(Status.MATCHED);

                buyerAssetEntity.setSize(buyerAssetEntity.getSize().add(size));
                buyerAssetEntity.setUsableSize(buyerAssetEntity.getUsableSize().add(size));
                buyerTryAssetEntity.setSize(buyerTryAssetEntity.getSize().subtract(size.multiply(price)));

                existingAssetEntity.setSize(existingAssetEntity.getSize().subtract(size));
                existingAssetEntity.setUsableSize(existingAssetEntity.getUsableSize().subtract(size));
                existingTryAssetEntity.setSize(existingTryAssetEntity.getSize().add(size.multiply(price)));
                existingTryAssetEntity.setUsableSize(existingTryAssetEntity.getUsableSize().add(size.multiply(price)));

                orderRepository.save(newOrderEntity);
                orderRepository.save(matchedBuyOrderEntity);

                assetRepository.save(buyerAssetEntity);
                assetRepository.save(buyerTryAssetEntity);
                assetRepository.save(existingAssetEntity);
                assetRepository.save(existingTryAssetEntity);

            } else {
                existingAssetEntity.setUsableSize(existingAssetEntity.getUsableSize().subtract(size));

                assetRepository.save(existingAssetEntity);
            }

        } else if (Side.BUY.equals(orderSide)) {
            var existingAssetEntity = assetRepository
                    .findByCustomerIdAndAssetName(newOrderEntity.getCustomerId(), newOrderEntity.getAssetName())
                    .orElse(null);

            if(existingAssetEntity == null) {
                existingAssetEntity = new AssetEntity();
                existingAssetEntity.setCustomerId(newOrderEntity.getCustomerId());
                existingAssetEntity.setAssetName(newOrderEntity.getAssetName());
                existingAssetEntity.setSize(BigDecimal.ZERO);
                existingAssetEntity.setUsableSize(BigDecimal.ZERO);
            }

            var existingTryAssetEntity = assetRepository
                    .findByCustomerIdAndAssetName(newOrderEntity.getCustomerId(), AssetUtil.TRY)
                    .orElseThrow(() -> new BrokerageException(ExceptionMessage.PARAM_ASSET_NOT_FOUND, AssetUtil.TRY));

            if(existingTryAssetEntity.getUsableSize().subtract(size.multiply(price)).compareTo(BigDecimal.ZERO) < 0) {
                throw new BrokerageException(ExceptionMessage.PARAM_ASSET_NOT_ENOUGH, AssetUtil.TRY);
            }

            var matchedSellOrderEntityList = orderRepository
                    .findMatchedOrder(Side.SELL, newOrderEntity.getAssetName(), size, price, newOrderEntity.getCustomerId());

            var matchedSellOrderEntity = !matchedSellOrderEntityList.isEmpty() ? matchedSellOrderEntityList.getFirst() : null;

            if(matchedSellOrderEntity != null) {
                var sellerTryAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(matchedSellOrderEntity.getCustomerId(), AssetUtil.TRY)
                        .orElseThrow(() -> new BrokerageException(ExceptionMessage.PARAM_ASSET_NOT_FOUND, AssetUtil.TRY));

                var sellerAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(matchedSellOrderEntity.getCustomerId(), matchedSellOrderEntity.getAssetName())
                        .orElseThrow(() -> new BrokerageException(ExceptionMessage.ASSET_NOT_FOUND));

                newOrderEntity.setStatus(Status.MATCHED);
                matchedSellOrderEntity.setStatus(Status.MATCHED);

                sellerAssetEntity.setSize(sellerAssetEntity.getSize().subtract(size));
                sellerTryAssetEntity.setSize(sellerTryAssetEntity.getSize().add(size.multiply(price)));
                sellerTryAssetEntity.setUsableSize(sellerTryAssetEntity.getUsableSize().add(size.multiply(price)));

                existingAssetEntity.setSize(existingAssetEntity.getSize().add(size));
                existingAssetEntity.setUsableSize(existingAssetEntity.getUsableSize().add(size));
                existingTryAssetEntity.setSize(existingTryAssetEntity.getSize().subtract(size.multiply(price)));
                existingTryAssetEntity.setUsableSize(existingTryAssetEntity.getUsableSize().subtract(size.multiply(price)));

                orderRepository.save(newOrderEntity);
                orderRepository.save(matchedSellOrderEntity);

                assetRepository.save(sellerAssetEntity);
                assetRepository.save(sellerTryAssetEntity);
                assetRepository.save(existingAssetEntity);
                assetRepository.save(existingTryAssetEntity);

            } else {
                existingTryAssetEntity.setUsableSize(existingTryAssetEntity.getUsableSize().subtract(size.multiply(price)));

                assetRepository.save(existingTryAssetEntity);
            }

        } else {
            throw new BrokerageException(ExceptionMessage.ORDER_SIDE_IS_FALSE);
        }

        return orderRepository.save(newOrderEntity);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        var existingOrderEntity = orderRepository.findById(orderId).orElseThrow(() -> new BrokerageException(ExceptionMessage.RECORD_NOT_FOUND));

        var customerId = webUtils.getLoginnedCustomerId();

        if(!customerId.equals(existingOrderEntity.getCustomerId())) {
            throw new BrokerageException(ExceptionMessage.ORDER_DOES_NOT_BELONG_TO_THIS_USER);
        }

        if(existingOrderEntity.getStatus().equals(Status.PENDING)) {

            if(Side.SELL.equals(existingOrderEntity.getOrderSide())) {
                var existingAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(existingOrderEntity.getCustomerId(), existingOrderEntity.getAssetName())
                        .orElseThrow(() -> new BrokerageException(ExceptionMessage.ASSET_NOT_FOUND));

                existingAssetEntity.setUsableSize(existingAssetEntity.getUsableSize().add(existingOrderEntity.getSize()));

                assetRepository.save(existingAssetEntity);
            } else if (Side.BUY.equals(existingOrderEntity.getOrderSide())) {
                var existingTryAssetEntity = assetRepository
                        .findByCustomerIdAndAssetName(existingOrderEntity.getCustomerId(), AssetUtil.TRY)
                        .orElseThrow(() -> new BrokerageException(ExceptionMessage.ASSET_NOT_FOUND));

                existingTryAssetEntity.setUsableSize(existingTryAssetEntity.getUsableSize().add(existingOrderEntity.getSize().multiply(existingOrderEntity.getPrice())));

                assetRepository.save(existingTryAssetEntity);
            } else {
                throw new BrokerageException(ExceptionMessage.ORDER_SIDE_IS_FALSE);
            }

            existingOrderEntity.setStatus(Status.CANCELLED);
            orderRepository.save(existingOrderEntity);
        } else {
            throw new BrokerageException(ExceptionMessage.ORDER_CANNOT_BE_CANCELLED);
        }
    }

    @Override
    public List<OrderDto> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate) {
        var orderEntityList = orderRepository.findByCustomerIdAndCreateDateBetween(customerId, startDate.atStartOfDay(), endDate.atTime(23,59,59));

        return orderEntityList.stream().map(orderMapper::toDto).toList();
    }


}
