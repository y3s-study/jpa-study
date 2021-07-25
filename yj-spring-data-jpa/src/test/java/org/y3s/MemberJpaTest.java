package org.y3s;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class MemberJpaTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberRepository repository;

    @Before
    public void setUp() {
        em.persist(new Member("jun"));
    }

    @Test
    public void find() {
        repository.findAll().forEach(System.out::println);
    }
}
