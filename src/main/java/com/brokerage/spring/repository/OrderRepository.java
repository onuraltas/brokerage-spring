package com.brokerage.spring.repository;

import com.brokerage.spring.entity.OrderEntity;
import com.brokerage.spring.enums.Side;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query("SELECT o FROM OrderEntity o WHERE o.orderSide=:orderSide and o.assetName=:assetName and o.status = 'PENDING' and o.size=:size and o.price=:price and o.customerId!=:customerId")
    List<OrderEntity> findMatchedOrder(@Param("orderSide") Side orderSide, @Param("assetName") String assetName, @Param("size") BigDecimal size, @Param("price") BigDecimal price, @Param("customerId") Integer customerId);

    List<OrderEntity> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDateTime startDate, LocalDateTime endDate);
}
