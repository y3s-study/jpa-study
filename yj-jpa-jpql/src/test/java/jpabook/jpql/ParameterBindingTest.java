package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class ParameterBindingTest extends JpaTest {
    @Test
    void parameterByName() {
        String usernameParam = "user1";

        em.createQuery("SELECT m FROM Member m WHERE m.username = :username", Member.class)
                .setParameter("username", usernameParam)
                .getResultList();
    }

    @Test
    void parameterByPosition() {
        String usernameParam = "user1";

        em.createQuery("SELECT m FROM Member m WHERE m.username = ?1", Member.class)
                .setParameter(1, usernameParam)
                .getResultList();
    }
}
