package com.brokerage.spring.service;

import com.brokerage.spring.dto.OrderDto;
import com.brokerage.spring.dto.request.CreateOrderRequestDto;
import com.brokerage.spring.dto.response.CreateOrderResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    CreateOrderResponseDto createBuyOrder(CreateOrderRequestDto requestDto);

    CreateOrderResponseDto createSellOrder(CreateOrderRequestDto requestDto);

    void cancelOrder(Integer orderId);

    List<OrderDto> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate);

}
