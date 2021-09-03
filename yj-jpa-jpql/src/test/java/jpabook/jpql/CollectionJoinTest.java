package jpabook.jpql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CollectionJoinTest extends JpaTest {
    @BeforeEach
    void setUp() {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member member1 = new Member("jun", 22, teamA);
        em.persist(member1);

        Member member2 = new Member("mark", 33, teamA);
        em.persist(member2);
    }

    @Test
    void collectionJoin() {
        List<Object[]> resultList = em.createQuery("SELECT t, m FROM Team t LEFT JOIN t.members m", Object[].class)
                .getResultList();

        for (Object[] row : resultList) {
            Team team = (Team) row[0];
            Member member = (Member) row[1];

            System.out.println("Team: " + team);
            System.out.println("Member: " + member);
        }
    }
}
