package com.brokerage.spring.controller.impl;

import com.brokerage.spring.controller.OrderController;
import com.brokerage.spring.dto.OrderDto;
import com.brokerage.spring.dto.request.CreateOrderRequestDto;
import com.brokerage.spring.dto.response.CreateOrderResponseDto;
import com.brokerage.spring.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<CreateOrderResponseDto> buy(CreateOrderRequestDto requestDto) {
        return new ResponseEntity<>(orderService.createBuyOrder(requestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CreateOrderResponseDto> sell(CreateOrderRequestDto requestDto) {
        return new ResponseEntity<>(orderService.createSellOrder(requestDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> cancel(Integer orderId) {
        orderService.cancelOrder(orderId);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OrderDto>> findByCustomerId(Integer customerId, LocalDate startDate, LocalDate endDate) {
        return ResponseEntity.ok(orderService.findByCustomerIdAndCreateDateBetween(customerId, startDate, endDate));
    }


}
