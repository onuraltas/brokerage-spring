package com.brokerage.spring.service;

import com.brokerage.spring.dto.AssetDto;

import java.util.List;

public interface AssetService {

    List<AssetDto> findByCustomerId(Integer customerId);
}
