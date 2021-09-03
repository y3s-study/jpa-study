package jpabook.jpql;

import org.junit.jupiter.api.Test;

import java.util.List;

public class JoinQueryTest extends JpaTest {
    @Test
    void innerJoin() {
        em.createQuery("SELECT m FROM Member m INNER JOIN m.team t WHERE t.name = :teamName", Member.class)
                .setParameter("teamName", "팀A")
                .getResultList();
    }

    @Test
    void innerJoin2() {
        var jpql = "SELECT m, t FROM Member m INNER JOIN m.team t";

        List<Object[]> resultList = em.createQuery(jpql, Object[].class)
                .getResultList();

        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            Team team = (Team) row[1];
        }
    }

    @Test
    void outerJoin() {
        em.createQuery("SELECT m FROM Member m LEFT OUTER JOIN m.team t", Member.class)
                .getResultList();
    }
}
