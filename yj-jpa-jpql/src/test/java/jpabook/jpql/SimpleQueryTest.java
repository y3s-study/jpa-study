package jpabook.jpql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleQueryTest extends JpaTest {

    @BeforeEach
    void setUp() {
        em.persist(new Member("jun", 25));
        em.persist(new Member("peter", 32));
    }

    @Test
    void typedQuery() {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);

        List<Member> members = query.getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
        }

        assertEquals(2, members.size());
    }

    @Test
    void query() {
        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");

        @SuppressWarnings("rawtypes")
        List results = query.getResultList();
        for (Object o : results) {
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);
        }
    }

    @Test
    @DisplayName("별칭을 지정하지 않으면 예외가 발생한다.")
    void queryWithoutAlias() {
        assertThrows(IllegalArgumentException.class, () -> {
            TypedQuery<Member> query = em.createQuery("SELECT m FROM Member", Member.class);
            query.getResultList();
        });
    }

    @Test
    @DisplayName("getSingleResult()의 반환 결과가 정확히 1개가 아니면 예외가 발생한다.")
    void singleQuery() {
        var exception = assertThrows(
                NonUniqueResultException.class,
                () -> em.createQuery("SELECT m FROM Member m", Member.class).getSingleResult()
        );

        assertEquals("query did not return a unique result: 2", exception.getMessage());
    }
}
