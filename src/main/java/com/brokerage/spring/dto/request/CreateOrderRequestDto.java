package com.brokerage.spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequestDto {

    @NotBlank
    private String assetName;

    @NotNull
    @Positive
    private BigDecimal size;

    @NotNull
    @Positive
    private BigDecimal price;

}
