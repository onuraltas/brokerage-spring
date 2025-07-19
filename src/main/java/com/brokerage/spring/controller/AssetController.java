package com.brokerage.spring.controller;

import com.brokerage.spring.dto.AssetDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/rest/asset")
public interface AssetController {

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<AssetDto>> findByCustomerId(@NotNull @Positive @PathVariable Integer customerId);

}
