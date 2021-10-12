package jpabook.ch14.listener;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

class ListenerTest extends JpaTest {

	@Test
	void 이벤트_테스트() {
		Duck duck = new Duck();
		duck.setName("duck");

		em.persist(duck);
		em.flush();
		em.clear();
	}
}