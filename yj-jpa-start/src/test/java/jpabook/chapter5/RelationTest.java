package jpabook.chapter5;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RelationTest {
    static EntityManagerFactory emf;
    EntityManager em;

    @Test
    void testSave() {
        // 팀 1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    @Test
    void queryJoin() {
        testSave();

        em.flush();
        em.clear();

        String jpql = "select m from Ch5Member m join m.team t where t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" + member.getUsername());
        }
    }

    @Test
    void updateRelation() {
        testSave();

        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    @Test
    void deleteRelation() {
        testSave();

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);
    }

    @Test
    void testSaveNonOwningSide() {
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Team team1 = new Team("team1", "팀1");
        team1.getMembers().add(member1);

        em.persist(team1);
    }

    @Test
    void test순수한객체_양방향() {
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1);
        team1.getMembers().add(member1);

        member2.setTeam(team1);
        team1.getMembers().add(member2);

        List<Member> members = team1.getMembers();
        System.out.println("members.size = " + members.size());
    }

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }
}
