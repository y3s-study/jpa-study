package jpabook.ch6.manytomany.oneway;

import java.util.List;

import javax.persistence.EntityManager;

class ManyToManyTest {
	private EntityManager em;

	public void save() {
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품A");
		em.persist(productA);

		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		member1.getProducts().add(productA);
		em.persist(member1);
	}

	public void find() {
		Member member = em.find(Member.class, "member1");
		List<Product> products = member.getProducts();
		for (Product product : products) {
			System.out.println("procut.name = " + product.getName());
		}
	}


}