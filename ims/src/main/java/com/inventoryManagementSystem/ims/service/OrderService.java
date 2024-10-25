package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            updatedOrder.setO_id(orderId);  // Ensure the ID remains the same for the update
            return orderRepository.save(updatedOrder);
        }
        return null;  // or throw an exception if appropriate
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
