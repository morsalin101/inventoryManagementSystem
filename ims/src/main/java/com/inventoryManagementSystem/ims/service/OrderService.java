package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> saveOrders(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    public List<Order> getUniqueOrders() {
        // Get all orders and collect unique order IDs
        return orderRepository.findAll().stream()
                .collect(Collectors.toMap(Order::getOrderId, order -> order, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
   
    public void updateStatusToPaidForOrderId(String order_Id) {
        // Fetch all orders with the given order_id
        List<Order> orders = orderRepository.findAllByOrderId(order_Id);
        for (Order order : orders) {
            order.setStatus("Paid"); // Update status to "Paid"
        }
        orderRepository.saveAll(orders); // Save all updated orders
    }
    public boolean deleteOrderByOrderId(String orderId) {
        List<Order> orders = orderRepository.findAllByOrderId(orderId);
        if (!orders.isEmpty()) {
            orderRepository.deleteAll(orders); // Deletes all orders with the specified orderId
            return true;
        }
        return false; // No orders found with the specified orderId
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByOrderId(String orderId) {
        return orderRepository.findAllByOrderId(orderId);
    }
    
    public long countUniqueOrders() {
        return orderRepository.countUniqueOrders();
    }

    // Count unique paid orders
    public long countUniquePaidOrders() {
        return orderRepository.countUniqueOrdersByStatus("Paid");
    }

    // Count unique pending orders
    public long countUniquePendingOrders() {
        return orderRepository.countUniqueOrdersByStatus("Pending");
    }
    
     // Calculate total paid amount
     public double calculateTotalPaid() {
        Double totalPaid = orderRepository.calculateTotalPaid();
        return totalPaid != null ? totalPaid : 0.0;
    }

    // Calculate total unpaid amount
    public double calculateTotalUnpaid() {
        Double totalUnpaid = orderRepository.calculateTotalUnpaid();
        return totalUnpaid != null ? totalUnpaid : 0.0;
    }
}
