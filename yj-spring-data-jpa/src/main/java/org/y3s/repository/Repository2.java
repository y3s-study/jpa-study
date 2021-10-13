package org.y3s.repository;

import org.springframework.stereotype.Repository;
import org.y3s.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class Repository2 {
    @PersistenceContext
    private EntityManager em;

    public Member findMember() {
        return em.find(Member.class, 1L);
    }
}
