package com.sto.controller.rest;

import com.sto.model.dto.OrderDto;
import com.sto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public void create(OrderDto orderDto) {
        orderService.createOrder(orderDto);
    }
}
