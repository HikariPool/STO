package com.sto.service.impl;

import com.sto.model.dto.OrderDto;
import com.sto.model.entity.business.Order;
import com.sto.repository.OrderRepo;
import com.sto.repository.SparePartsRepo;
import com.sto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sto.data.constants.Constants.CHECK_ORDER_LINK;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${mail.subject.order_created}")
    private String orderCreatedSubject;
    @Value("${mail.template.order_created}")
    private String orderCreatedMailTemplate;
    @Value("${mail.subject.new_order}")
    private String newOrderSubject;
    @Value("${mail.template.new_order}")
    private String newOrderMailTemplate;
    @Value("${spring.mail.username}")
    private String adminEmail;
    @Value("${server.host}")
    private String serverHost;

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SparePartsRepo sparePartsRepo;
    @Autowired
    private MailSender mailSender;


    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {
        Order order = saveOrder(orderDto);

        mailSender.send(order.getCreatedBy().getEmail(), orderCreatedSubject,
                String.format(orderCreatedMailTemplate, order.getId()));

        String newOrderMail = String.format(newOrderMailTemplate, order.getId(), order.getAddress(),
                (serverHost + CHECK_ORDER_LINK + order.getSparePart().getId()));

        mailSender.send(adminEmail, newOrderSubject, newOrderMail);
    }

    private Order saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setSparePart(sparePartsRepo.findById(orderDto.getProductId()).get());
        order.setAddress(orderDto.getAddress());

        return orderRepo.saveAndFlush(order);
    }
}
