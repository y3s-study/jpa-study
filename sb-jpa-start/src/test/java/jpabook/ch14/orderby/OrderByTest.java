package jpabook.ch14.orderby;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Sets;

import jpabook.JpaTest;

class OrderByTest extends JpaTest {
	@Test
	void order_by() {
		Team team = new Team("test");
		Member member = new Member("member");
		team.setMembers(Sets.newHashSet(member));
		em.persist(team);
		em.flush();
		em.clear();

		Team findTeam = em.find(Team.class, 1L);
		findTeam.getMembers().size();
	}

}