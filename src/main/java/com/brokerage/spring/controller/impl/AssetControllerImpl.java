package com.brokerage.spring.controller.impl;

import com.brokerage.spring.controller.AssetController;
import com.brokerage.spring.dto.AssetDto;
import com.brokerage.spring.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AssetControllerImpl implements AssetController {

    private final AssetService assetService;

    @Override
    public ResponseEntity<List<AssetDto>> findByCustomerId(Integer customerId) {
        return ResponseEntity.ok(assetService.findByCustomerId(customerId));
    }

}
