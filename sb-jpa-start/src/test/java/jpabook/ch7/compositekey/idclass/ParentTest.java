package jpabook.ch7.compositekey.idclass;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

class ParentTest {
	private EntityManager em;

	@Test
	void save() {
		Parent parent = new Parent();
		parent.setId1("myId1");
		parent.setId2("myId2");
		parent.setName("parentName");
		em.persist(parent);
	}

	@Test
	void read() {
		ParentId parentId = new ParentId("myId1", "myId2");
		Parent parent = em.find(Parent.class, parentId);
	}
}