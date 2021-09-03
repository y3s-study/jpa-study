package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class ThetaJoinTest extends JpaTest {
    @Test
    void thetaJoin() {
        em.createQuery("SELECT COUNT(m) FROM Member m, Team t WHERE m.username = t.name", Long.class)
                .getSingleResult();
    }
}
