package org.y3s.repository;

import org.springframework.stereotype.Repository;
import org.y3s.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class Repository1 {
    @PersistenceContext
    private EntityManager em;

    public void hello() {
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        System.out.println("hello, " + members);
    }
}
