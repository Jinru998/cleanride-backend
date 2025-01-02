package com.cleanride.services;

import com.cleanride.domain.Order;
import jakarta.inject.Singleton;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;

@Singleton
public interface OrderService {

    Page<Order> getOrders(Specification specification, Pageable pageable);
}
