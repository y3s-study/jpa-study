package jpabook.ch6.onetomany.oneway;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

class OneToManyTest {
	private EntityManager em;

	public void testSave() {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Member member1 = new Member("member1");
		Member member2 = new Member("member1");

		Team team1 = new Team("team1");
		team1.getMembers().add(member1);
		team1.getMembers().add(member2);

		em.persist(member1);	// insert member1
		em.persist(member2);	// insert member2
		em.persist(team1);		// insert team1, update member1.fk, member2.fk

		transaction.commit();
	}


}