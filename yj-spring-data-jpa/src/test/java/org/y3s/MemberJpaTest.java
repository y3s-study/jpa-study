package org.y3s;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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

    @Test
    public void pagingTest() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name"));

        Page<Member> result = repository.findAll(pageRequest);

        List<Member> members = result.getContent(); // 조회된 데이터
        int totalPages = result.getTotalPages(); // 전체 페이지 수
        boolean hasNextPage = result.hasNext(); // 다음 페이지 존재 여부
    }

    @Test
    public void specificationTest() {
        List<Member> members = repository.findAll(MemberSpec.memberName("name"));
    }
}
