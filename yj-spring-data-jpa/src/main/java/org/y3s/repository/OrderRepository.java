package org.y3s.repository;

import org.springframework.stereotype.Repository;
import org.y3s.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;

    public Order findOrder(Long id) {
        return em.find(Order.class, id);
    }
}
