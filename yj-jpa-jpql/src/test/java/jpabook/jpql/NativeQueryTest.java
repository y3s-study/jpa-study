package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class NativeQueryTest extends JpaTest {
    @Test
    void nativeQuery() {
        em.createNativeQuery("select id, age, username, team_id from member where age > :age", Member.class)
                .setParameter("age", 20)
                .getResultList();
    }
}
