package jpabook.chapter8;

import jpabook.JpaTest;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyTest extends JpaTest {
    @DisplayName("프록시 객체의 메소드를 호출하면 프록시 객체를 초기화한다.")
    @Test
    void getReference() {
        Team team = new Team();
        team.setName("my team");

        em.persist(team);
        em.flush();
        em.clear();

        Team proxyTeam = em.getReference(Team.class, team.getPk());
        System.out.println("entity class : " + proxyTeam.getClass().getName());

        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
        System.out.println("team name : " + proxyTeam.getName());
        assertTrue(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
    }

    @Test
    void getReference_in_persistenceContext() {
        Team team = new Team();
        team.setName("my team");

        em.persist(team);

        Team proxyTeam = em.getReference(Team.class, team.getPk());
        System.out.println("team name : " + proxyTeam.getName());
    }

    @DisplayName("영속 상태가 아닌 엔티티를 초기화하면 LazyInitializationException 예외가 발생한다.")
    @Test
    void initializeEntity() {
        Team team = new Team();
        team.setName("my team");

        em.persist(team);
        em.flush();
        em.clear();

        Team proxyTeam = em.getReference(Team.class, team.getPk());
        em.detach(proxyTeam);

        assertThrows(LazyInitializationException.class, () -> {
            System.out.println("team name : " + proxyTeam.getName());
        });
    }

    @DisplayName("프록시 객체의 식별자 값을 조회해도 프록시를 초기화 하지 않는다. (필드 접근 방식)")
    @Test
    void getId() {
        Team team = new Team();
        team.setName("my team");

        em.persist(team);
        em.flush();
        em.clear();

        Team proxyTeam = em.getReference(Team.class, team.getPk());

        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
        System.out.println("proxy id = " + proxyTeam.getPk());
        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
        Hibernate.initialize(proxyTeam);
        assertTrue(Hibernate.isInitialized(proxyTeam));
    }

    @DisplayName("프록시 객체의 식별자 값을 조회해도 프록시를 초기화 하지 않는다. (필드 접근 방식)")
    @Test
    void lazyLoading() {
        Team team = new Team();
        team.setName("my team");
        em.persist(team);

        Member member = new Member();
        member.setUsername("new member");
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());
        assertFalse(Hibernate.isInitialized(findMember.getTeam()));

        System.out.println("team name : " + findMember.getTeam().getName());
        assertTrue(Hibernate.isInitialized(findMember.getTeam()));
    }

    @Test
    void collectionTest() {
        Member member = new Member();
        member.setUsername("new member");
        em.persist(member);

        Order order1 = new Order();
        order1.setMember(member);
        order1.setJun("11111");
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member);
        order2.setJun("322322");
        em.persist(order2);


        em.flush();
        em.detach(order1);
        em.detach(order2);
        em.detach(member);
        em.clear();


        Member findMember = em.find(Member.class, member.getId());
        System.out.println("---------------------------------------");
        assertFalse(Hibernate.isInitialized(findMember.getOrders()));

        System.out.println("222222222");
        System.out.println("orders class name : " + findMember.getOrders().getClass().getName());
        System.out.println("222222222");
        assertFalse(Hibernate.isInitialized(findMember.getOrders()));

        System.out.println("33333333");
        System.out.println("orders.get(0) : " + findMember.getOrders().get(0));
        System.out.println("33333333");
        assertTrue(Hibernate.isInitialized(findMember.getOrders()));
    }
}
