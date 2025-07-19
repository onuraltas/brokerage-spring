package com.brokerage.spring.repository;

import com.brokerage.spring.entity.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<AssetEntity, Integer> {

    Optional<AssetEntity> findByCustomerIdAndAssetName(@Param("customerId") Integer customerId, @Param("assetName") String assetName);

    List<AssetEntity> findByCustomerId(Integer customerId);

}
