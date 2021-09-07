package jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class JpaTest {
	protected static EntityManagerFactory emf;
	protected EntityManager em;

	@BeforeAll
	static void beforeAll() {
		emf = Persistence.createEntityManagerFactory("jpabook");
	}

	@BeforeEach
	void setUp() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	@AfterEach
	void tearDown() {
		em.getTransaction().rollback();
		em.close();
	}

	@AfterAll
	static void afterAll() {
		emf.close();
	}

}

