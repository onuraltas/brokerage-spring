package com.brokerage.spring.mapper;

import com.brokerage.spring.dto.AssetDto;
import com.brokerage.spring.entity.AssetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper {

    AssetEntity toEntity(AssetDto assetDto);

    AssetDto toDto(AssetEntity assetEntity);

}
