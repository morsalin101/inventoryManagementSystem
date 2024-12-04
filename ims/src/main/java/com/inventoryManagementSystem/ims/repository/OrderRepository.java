package com.inventoryManagementSystem.ims.repository;

import com.inventoryManagementSystem.ims.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom method to find all orders by order_id
    List<Order> findAllByOrderId(String orderId);

      // Count unique orders by status
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM Order o WHERE o.status = :status")
    long countUniqueOrdersByStatus(@Param("status") String status);

    // Count all unique orders
    @Query("SELECT COUNT(DISTINCT o.orderId) FROM Order o")
    long countUniqueOrders();

     
    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = 'Paid'")
    Double calculateTotalPaid();

    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE o.status = 'Pending'")
    Double calculateTotalUnpaid();
}
