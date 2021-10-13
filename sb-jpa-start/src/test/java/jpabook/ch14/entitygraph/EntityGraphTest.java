package jpabook.ch14.entitygraph;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

public class EntityGraphTest extends JpaTest {
	@Test
	void 엔티티_그래프_사용() {
		EntityGraph<?> graph = em.getEntityGraph("Order.withMember");

		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);

		Order order = em.find(Order.class, 1L, hints);
	}
}
