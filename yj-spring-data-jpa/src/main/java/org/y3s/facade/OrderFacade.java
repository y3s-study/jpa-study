package org.y3s.facade;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.y3s.domain.Order;
import org.y3s.service.OrderService;

@Component
public class OrderFacade {
    @Autowired
    private OrderService orderService;

    @Transactional
    public Order findOrder(Long id) {
        Order order = orderService.findOrder(id);
        Hibernate.initialize(order);
        return order;
    }
}
