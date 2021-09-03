package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class PagingQueryTest extends JpaTest {
    @Test
    void basicPagingQuery() {
        em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC", Member.class)
                .setFirstResult(10)
                .setMaxResults(20)
                .getResultList();
    }
}
