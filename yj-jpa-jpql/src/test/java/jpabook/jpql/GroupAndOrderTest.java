package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class GroupAndOrderTest extends JpaTest {
    @Test
    void groupByTest() {
        em.createQuery("SELECT t.name, COUNT(m.age), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) " +
                                "FROM Member m LEFT JOIN m.team t " +
                                "GROUP BY t.name",
                        Object[].class)
                .getResultList();
    }

    @Test
    void groupByHavingTest() {
        em.createQuery("SELECT t.name, COUNT(m.age), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) " +
                                "FROM Member m LEFT JOIN m.team t " +
                                "GROUP BY t.name " +
                                "HAVING AVG(m.age) >= 10",
                        Object[].class)
                .getResultList();
    }

    @Test
    void orderByTest() {
        em.createQuery("SELECT m FROM Member m ORDER BY m.age DESC, m.username ASC", Member.class)
                .getResultList();
    }
}
