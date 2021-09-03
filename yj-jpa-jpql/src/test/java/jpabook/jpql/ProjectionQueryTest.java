package jpabook.jpql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProjectionQueryTest extends JpaTest {

    @BeforeEach
    void setUp() {
        em.persist(new Member("jun", 25));
        em.persist(new Member("peter", 32));
    }

    @Test
    void embeddedTypeProjection() {
        em.createQuery("SELECT o.address FROM Order o ", Address.class)
                .getResultList();
    }

    @Test
    void scalaTypeProjection() {
        em.createQuery("SELECT m.username FROM Member m", String.class)
                .getResultList();
    }

    @Test
    void distinct() {
        em.createQuery("SELECT DISTINCT m.username FROM Member m", String.class)
                .getResultList();
    }

    @Test
    void statistics() {
        Double orderAmountAvg = em.createQuery("SELECT AVG(o.orderAmount) FROM Order o", Double.class)
                .getSingleResult();

        System.out.println(orderAmountAvg); // null
    }

    @Test
    void multipleSelect() {
        List<Object[]> resultList = em.createQuery("SELECT m.username, m.age FROM Member m", Object[].class)
                .getResultList();

        for (Object[] row : resultList) {
            System.out.println("username: " + row[0]);
            System.out.println("age: " + row[1]);
        }
    }

    @Test
    void newProjection() {
        List<UserDto> resultList = em.createQuery(
                "SELECT new jpabook.jpql.UserDto(m.username, m.age) FROM Member m",
                        UserDto.class)
                .getResultList();

        for (UserDto userDto : resultList) {
            System.out.println("username:" + userDto.getUsername());
            System.out.println("age:" + userDto.getAge());
        }
    }
}
