package org.y3s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.y3s.domain.Order;
import org.y3s.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order findOrder(Long id) {
        return orderRepository.findOrder(id);
    }
}
