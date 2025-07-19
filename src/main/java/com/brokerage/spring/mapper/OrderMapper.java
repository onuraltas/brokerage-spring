package com.brokerage.spring.mapper;

import com.brokerage.spring.dto.OrderDto;
import com.brokerage.spring.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity toEntity(OrderDto orderDto);

    OrderDto toDto(OrderEntity orderEntity);

}
