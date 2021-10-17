package org.y3s;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.y3s.domain.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProxyTest {

	@PersistenceContext
	EntityManager em;

	@Test
	public void 영속성컨텍스트와_프록시() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, newMember.getId());
		Member findMember = em.find(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());
		System.out.println("findMember Type = " + findMember.getClass());

		assertTrue(refMember == findMember);
	}

	@Test
	public void 영속성컨텍스트와_프록시2() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member findMember = em.find(Member.class, newMember.getId());
		Member refMember = em.getReference(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());
		System.out.println("findMember Type = " + findMember.getClass());

		assertTrue(refMember == findMember);
	}

	@Test
	public void 프록시_타입비교() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());

		assertTrue(refMember instanceof Member);
	}

	@Test
	public void 프록시와_동등성비교() {
		Member saveMember = new Member("회원1");
		em.persist(saveMember);
		em.flush();
		em.clear();

		Member newMember = new Member("회원1");
		Member refMember = em.getReference(Member.class, saveMember.getId());

		assertTrue(newMember.equals(refMember));
	}
}
