package jpabook.ch16;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;

import jpabook.JpaTest;
import jpabook.ch16.cache.Member;

public class SecondaryCache extends JpaTest {
	void entity_manager() {
		EntityManager em = emf.createEntityManager();

		em.setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
		em.setProperty("javax.persistence.cache.storeMode", CacheRetrieveMode.BYPASS);
	}

	void find() {
		Map<String, Object> param = new HashMap<>();
		param.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
		param.put("javax.persistence.cache.storeMode", CacheRetrieveMode.BYPASS);

		em.find(Member.class, 1L, param);
	}

	void jpql() {
		em.createQuery("select m from Member m where m.id = :id",
				Member.class)
			.setParameter("id", 1L)
			.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
			.setHint("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS)
			.getSingleResult();
	}

	void 관리_객체_조회() {
		Cache cache = emf.getCache();
		boolean contains = cache.contains(Member.class, 1L);
		System.out.println("contains = " + contains);
	}
}
