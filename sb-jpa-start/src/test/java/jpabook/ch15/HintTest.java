package jpabook.ch15;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

public class HintTest extends JpaTest {
	@Test
	void sql_쿼리_힌트() {
		Session session = em.unwrap(Session.class);

		List<Member> list = session.createQuery("select m from Member m")
			.addQueryHint("FULL (MEMBER)")
			.list();
	}
}
