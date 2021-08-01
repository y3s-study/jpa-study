package jpabook.ch6.manytomany.connectentity.primarykey;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

class ConnectEntityWithPrimaryKeyTest {
	private EntityManager em;

	public void save() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);

		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);

		Order order = new Order();
		order.setMember(member1);
		order.setProduct(productA);
		order.setOrderAmount(2);
		em.persist(order);
	}

	public void find() {
		Long orderId = 1L;
		Order order = em.find(Order.class, orderId);

		Member member = order.getMember();
		Product product = order.getProduct();

		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("orderAmount = " + order.getOrderAmount());
	}


}