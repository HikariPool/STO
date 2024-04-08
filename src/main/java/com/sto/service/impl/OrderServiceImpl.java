package com.sto.service.impl;

import com.sto.model.dto.OrderDto;
import com.sto.model.entity.business.Order;
import com.sto.repository.OrderRepo;
import com.sto.repository.SparePartsRepo;
import com.sto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SparePartsRepo sparePartsRepo;


    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Order order = saveOrder(orderDto);

    }

    private Order saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setSparePart(sparePartsRepo.findById(orderDto.getProductId()).get());
        order.setAddress(order.getAddress());

        return orderRepo.saveAndFlush(order);
    }
}
