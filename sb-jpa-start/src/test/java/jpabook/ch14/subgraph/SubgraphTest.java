package jpabook.ch14.subgraph;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

public class SubgraphTest extends JpaTest {
	@Test
	void subgraph_사용() {
		Map<String, Object> hints = new HashMap<>();
		hints.put("javax.persistence.fetchgraph", em.getEntityGraph("Order.withAll"));

		Order order = em.find(Order.class, 1L, hints);
	}

	@Test
	void JPQL_엔티티_그래프_힌트() {
		em.createQuery("select o from Order o where o.id = :orderId",
				Order.class)
			.setParameter("orderId", 1L)
			.setHint("javax.persistence.fetchgraph", em.getEntityGraph("Order.withAll"))
			.getResultList();
	}
}
