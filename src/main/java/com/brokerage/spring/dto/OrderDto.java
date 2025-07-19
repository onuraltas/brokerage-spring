package com.brokerage.spring.dto;

import com.brokerage.spring.enums.Side;
import com.brokerage.spring.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Integer orderId;

    private Integer customerId;

    private String assetName;

    private Side orderSide;

    private BigDecimal size;

    private BigDecimal price;

    private Status status;

    private LocalDateTime createDate;

}
