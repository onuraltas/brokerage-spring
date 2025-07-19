package com.brokerage.spring.service.impl;

import com.brokerage.spring.dto.AssetDto;
import com.brokerage.spring.mapper.AssetMapper;
import com.brokerage.spring.repository.AssetRepository;
import com.brokerage.spring.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    @Override
    public List<AssetDto> findByCustomerId(Integer customerId) {
        var assetEntityList = assetRepository.findByCustomerId(customerId);

        return assetEntityList.stream().map(assetMapper::toDto).toList();
    }

}
