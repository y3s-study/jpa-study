package org.y3s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.y3s.Member;
import org.y3s.repository.Repository1;
import org.y3s.repository.Repository2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class HelloService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private Repository1 repository1;
    @Autowired
    private Repository2 repository2;

    @Transactional
    public Member logic() {
        repository1.hello();
        return repository2.findMember();
    }
}
