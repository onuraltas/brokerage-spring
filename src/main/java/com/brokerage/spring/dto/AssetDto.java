package com.brokerage.spring.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetDto {

    private Integer assetId;

    private Integer customerId;

    private String assetName;

    private BigDecimal size;

    private BigDecimal usableSize;

}
