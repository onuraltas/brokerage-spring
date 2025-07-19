package com.brokerage.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assets", uniqueConstraints = @UniqueConstraint(name = "assets_unique", columnNames = {"customer_id", "assetName"}))
public class AssetEntity {

    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assetId;

    private Integer customerId;

    @Column(columnDefinition = "varchar(10)")
    private String assetName;

    private BigDecimal size;

    private BigDecimal usableSize;

    public AssetEntity(Integer customerId, String assetName, BigDecimal size, BigDecimal usableSize) {
        this.customerId = customerId;
        this.assetName = assetName;
        this.size = size;
        this.usableSize = usableSize;
    }
}
