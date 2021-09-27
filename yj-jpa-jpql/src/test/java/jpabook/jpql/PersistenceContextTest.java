package jpabook.jpql;

import org.junit.jupiter.api.Test;

import javax.persistence.FlushModeType;

public class PersistenceContextTest extends JpaTest {
    @Test
    void entity() {
        Member member = new Member();
        em.persist(member);

        Member findMember = em.find(Member.class, member.getId());

        Member jpqlMember = em.createQuery("select m from Member m", Member.class)
                .getSingleResult();
    }

    @Test
    void flushMode() {
        em.createQuery("select m from Member m", Member.class)
                .setFlushMode(FlushModeType.AUTO)
                .getResultList();
    }
}
