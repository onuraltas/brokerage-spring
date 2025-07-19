package com.brokerage.spring.entity;

import com.brokerage.spring.enums.Side;
import com.brokerage.spring.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Version
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private Integer customerId;

    @Column(columnDefinition = "varchar(10)")
    private String assetName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(4)")
    private Side orderSide;

    private BigDecimal size;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(10)")
    private Status status;

    private LocalDateTime createDate;

}
