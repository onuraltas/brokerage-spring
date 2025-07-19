package com.brokerage.spring.controller;

import com.brokerage.spring.dto.OrderDto;
import com.brokerage.spring.dto.request.CreateOrderRequestDto;
import com.brokerage.spring.dto.response.CreateOrderResponseDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/rest/order")
public interface OrderController {

    @PostMapping("/buy")
    ResponseEntity<CreateOrderResponseDto> buy(@Validated @RequestBody CreateOrderRequestDto requestDto);

    @PostMapping("/sell")
    ResponseEntity<CreateOrderResponseDto> sell(@Validated @RequestBody CreateOrderRequestDto requestDto);

    @DeleteMapping("/cancel/{orderId}")
    ResponseEntity<Void> cancel(@NotNull @Positive @PathVariable Integer orderId);

    @GetMapping("/{customerId}/{startDate}/{endDate}")
    ResponseEntity<List<OrderDto>> findByCustomerId(@NotNull @Positive @PathVariable Integer customerId, @NotNull @PathVariable LocalDate startDate, @NotNull @PathVariable LocalDate endDate);

}
