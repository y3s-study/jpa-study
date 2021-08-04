package jpabook.ch7.compositekey.embeddedid;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

class ParentTest {
	private EntityManager em;

	@Test
	void save() {
		Parent parent = new Parent();
		ParentId parentId = new ParentId("myId1", "myId2");
		parent.setId(parentId);
		parent.setName("parentName");
		em.persist(parent);
	}

	@Test
	void read() {
		ParentId parentId = new ParentId("myId1", "myId2");
		Parent parent = em.find(Parent.class, parentId);
	}
}
