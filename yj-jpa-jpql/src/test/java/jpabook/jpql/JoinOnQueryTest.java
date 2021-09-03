package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class JoinOnQueryTest extends JpaTest {
    @Test
    void joinOn() {
        em.createQuery("SELECT m, t FROM Member m LEFT JOIN m.team t ON t.name = 'A'", Object[].class)
                .getResultList();
    }
}
