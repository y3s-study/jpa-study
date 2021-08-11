package jpabook.ch8.cascade;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

class CascadeTest {
	private static void saveWithCascade(EntityManager em) {
		Child child1 = new Child();
		Child child2 = new Child();

		Parent parent = new Parent();
		child1.setParent(parent);
		child2.setParent(parent);
		parent.getChildren().add(child1);
		parent.getChildren().add(child2);

		// 부모 저장, 연관된 자식들 저장
		em.persist(parent);
	}

}