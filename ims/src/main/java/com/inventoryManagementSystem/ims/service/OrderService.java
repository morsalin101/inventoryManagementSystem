package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.Order;
import com.inventoryManagementSystem.ims.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
