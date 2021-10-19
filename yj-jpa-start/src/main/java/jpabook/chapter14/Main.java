package jpabook.chapter14;

import jpabook.start.JpaTemplate;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        JpaTemplate jpaTemplate = new JpaTemplate(emf);

        jpaTemplate.run(em -> {
            EntityGraph<?> entityGraph = em.getEntityGraph("Order.withMember");

            Map<String, Object> hints = new HashMap<>();
            hints.put("javax.persistence.fetchgraph", entityGraph);

            Order order = em.find(Order.class, 1L, hints);
        });


        jpaTemplate.run(em -> {
            em.createQuery("select o from Order o where o.id = :orderId", Order.class)
                    .setParameter("orderId", 1L)
                    .setHint("javax.persistence.fetchgraph", em.getEntityGraph("Order.withAll"))
                    .getResultList();
        });

    }
}
