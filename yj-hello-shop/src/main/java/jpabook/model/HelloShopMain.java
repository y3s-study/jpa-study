package jpabook.model;

import jpabook.model.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class HelloShopMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            logic(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        em.close();
        emf.close();
    }

    private static void logic(EntityManager em) {
        Member member = new Member();
        member.setCity("ccc");
        member.setName("jun");
        member.setStreet("ssssssss");
        member.setZipcode("zzzz");
        em.persist(member);

        Item item = new Item();
        item.setName("Water");
        item.setPrice(3000);
        item.setStockQuantity(9999);
        em.persist(item);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(100);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setItem(item);

        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        em.persist(order);
        em.persist(orderItem);

        em.flush();
        em.clear();

        Order findOrder = em.find(Order.class, order.getId());
        System.out.println("################## " + findOrder.getMember());
        System.out.println("################## " + findOrder.getOrderItems().get(0));
    }


}
