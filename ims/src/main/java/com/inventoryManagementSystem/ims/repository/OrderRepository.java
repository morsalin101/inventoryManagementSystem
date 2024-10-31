package com.inventoryManagementSystem.ims.repository;

import com.inventoryManagementSystem.ims.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
