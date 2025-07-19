package com.brokerage.spring.dto.response;

import com.brokerage.spring.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDto {

    private OrderDto order;

}
