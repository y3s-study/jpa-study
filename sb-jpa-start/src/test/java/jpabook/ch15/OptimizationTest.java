package jpabook.ch15;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

class OptimizationTest extends JpaTest {
	@Test
	void subSelect() {
		Member member = em.createQuery("select m from Member m where m.id > 10", Member.class)
			.getSingleResult();
	}

}