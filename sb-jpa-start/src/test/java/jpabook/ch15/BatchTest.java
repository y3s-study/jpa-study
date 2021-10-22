package jpabook.ch15;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

public class BatchTest extends JpaTest {
	@Test
	void JPA_등록_배치() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		for (int i = 0; i < 100000; i++) {
			Product product = new Product("item" + i, 10000);
			em.persist(product);

			if (i % 100 == 0) {
				em.flush();
				em.clear();
			}
		}

		tx.commit();
		em.close();
	}

	@Test
	void JPA_페이징_배치() {
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		int pageSize = 100;
		for (int i = 0; i < 10; i++) {
			List<Product> resultList = em.createQuery("select p from Product p", Product.class)
				.setFirstResult(i * pageSize)
				.setMaxResults(pageSize)
				.getResultList();

			// 비즈니스 로직 실행
			for (Product product : resultList) {
				product.setPrice(product.getPrice() + 100);
			}

			em.flush();
			em.clear();
		}

		tx.commit();
		em.close();
	}

	@Test
	void 하이버네이트_scroll_사용() {
		EntityTransaction tx = em.getTransaction();
		Session session = em.unwrap(Session.class);
		tx.begin();

		ScrollableResults scroll = session.createQuery("select p from Product p")
			.setCacheMode(CacheMode.IGNORE) // 2차 캐시 기능 끔
			.scroll(ScrollMode.FORWARD_ONLY);

		int count = 0;

		while (scroll.next()) {
			Product p = (Product)scroll.get(0);
			p.setPrice(p.getPrice() + 100);

			count++;
			if (count % 100 == 0) {
				session.flush();
				session.clear();
			}
		}

		tx.commit();
		session.close();
	}

	@Test
	void 하이버네이트_무상태_세션() {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		StatelessSession session = sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		ScrollableResults scroll = session.createQuery("select p from Product p", Product.class)
			.scroll();

		while (scroll.next()) {
			Product p = (Product)scroll.get(0);
			p.setPrice(p.getPrice() + 100);
			session.update(p); // 직접 호출해야 한다
		}
		tx.commit();
		session.close();
	}
}
