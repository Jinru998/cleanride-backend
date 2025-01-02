package com.cleanride.services.impl;

import com.cleanride.domain.Order;
import com.cleanride.repository.OrderRepository;
import com.cleanride.services.OrderService;
import jakarta.inject.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Singleton
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> getOrders(Specification specification, Pageable pageable) {
        if (specification != null) {
            return orderRepository.findAll(specification, pageable);
        }
        return orderRepository.findAll(pageable);
    }
}
