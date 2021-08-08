package jpabook.chapter8;

import jpabook.JpaTest;
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

        Team proxyTeam = em.getReference(Team.class, team.getId());

        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
        System.out.println("team name : " + proxyTeam.getName());
        assertTrue(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
    }

    @Test
    void getReference_in_persistenceContext() {
        Team team = new Team();
        team.setName("my team");

        em.persist(team);

        Team proxyTeam = em.getReference(Team.class, team.getId());
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

        Team proxyTeam = em.getReference(Team.class, team.getId());
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

        Team proxyTeam = em.getReference(Team.class, team.getId());

        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
        System.out.println("proxy id = " + proxyTeam.getId());
        assertFalse(emf.getPersistenceUnitUtil().isLoaded(proxyTeam));
    }
}
