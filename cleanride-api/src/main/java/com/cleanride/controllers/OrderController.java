package com.cleanride.controllers;


import com.cleanride.api.OrdersApi;
import com.cleanride.domain.Order;
import com.cleanride.model.OrderCollection;
import com.cleanride.model.OrderDetail;
import com.cleanride.services.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController implements OrdersApi {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderCollection> getOrders(
            @Min(0) @Parameter(name = "offset", description = "Number of items to skip before returning the results.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
            @Min(1) @Max(100) @Parameter(name = "limit", description = "Maximum number of items to return.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
    ) {
        // If offset is truly the page number, call PageRequest.of(offset, limit).
        // If offset is # of items to skip, you might do: PageRequest.of(offset / limit, limit).
        PageRequest pageRequest = PageRequest.of(offset, limit);

        // For now, passing 'null' for Specification (no filters applied).
        Page<Order> pageResult = orderService.getOrders(null, pageRequest);

        // Map domain objects (Order) to your API model (OrderCollection).
        // If you have a separate OrderDetail model, you'd map each domain Order to that object.
        // For simplicity, let’s assume OrderCollection has a list of something akin to OrderDetail.

        // Create the OrderCollection response object.
        OrderCollection orderCollection = new OrderCollection();
        // Set pagination metadata
        orderCollection.setOffset(offset);
        orderCollection.setLimit(limit);

        List<OrderDetail> mappedItems = pageResult.getContent().stream()
            .map(domainOrder -> {
                OrderDetail detail = new OrderDetail();
                detail.setId(String.valueOf(domainOrder.getId()));
                detail.setStatus(domainOrder.getStatus());
//                detail.setCreatedAt(
//                        OffsetDateTime.of(LocalDateTime.from(domainOrder.getCreatedAt()), ZoneOffset.UTC)
//                );
//                detail.setUpdatedAt(OffsetDateTime.from(domainOrder.getUpdatedAt()));
                // map other fields...
                return detail;
            })
            .toList();
        orderCollection.setItems(Collections.singletonList(mappedItems));
//        */

        return ResponseEntity.ok(orderCollection);
    }
}
