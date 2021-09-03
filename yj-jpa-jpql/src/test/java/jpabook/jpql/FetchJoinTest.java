package jpabook.jpql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FetchJoinTest extends JpaTest {
    @BeforeEach
    void setUp() {
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member member1 = new Member("jun", 22, teamA);
        em.persist(member1);

        Member member2 = new Member("mark", 33, teamA);
        em.persist(member2);
        em.flush();
        em.clear();
    }

    @Test
    void entityFetchJoin() {
        List<Member> members = em.createQuery("SELECT m FROM Member m JOIN FETCH m.team", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println("Member: " + member + ", " + "Team: " + member.getTeam());
        }
    }

    @Test
    void collectionFetchJoin() {
        em.createQuery("SELECT t FROM Team t join fetch t.members")
                .getResultList();
    }
}
