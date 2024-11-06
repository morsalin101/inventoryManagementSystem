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
    
}
