package com.inventoryManagementSystem.ims.repository;

import com.inventoryManagementSystem.ims.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom method to find all orders by order_id
    List<Order> findAllByOrderId(String orderId);
}
