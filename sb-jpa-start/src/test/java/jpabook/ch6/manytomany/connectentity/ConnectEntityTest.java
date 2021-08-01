package jpabook.ch6.manytomany.connectentity;

import javax.persistence.EntityManager;

import jpabook.ch6.manytomany.connectentity.compositekey.Member;
import jpabook.ch6.manytomany.connectentity.compositekey.MemberProduct;
import jpabook.ch6.manytomany.connectentity.compositekey.MemberProductId;
import jpabook.ch6.manytomany.connectentity.compositekey.Product;

class ConnectEntityTest {
	private EntityManager em;

	public void save() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);

		Product productA = new Product();
		productA.setId("productA");
		productA.setId("상품1");
		em.persist(productA);

		MemberProduct memberProduct = new MemberProduct();
		memberProduct.setMember(member1);
		memberProduct.setProduct(productA);
		memberProduct.setOrderAmount(2);

		em.persist(memberProduct);
	}

	public void find() {
		MemberProductId memberProductId = new MemberProductId();
		memberProductId.setMember("member1");
		memberProductId.setProduct("productA");

		MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

		Member member = memberProduct.getMember();
		Product product = memberProduct.getProduct();

		System.out.println("member = " + member.getUsername());
		System.out.println("product = " + product.getName());
		System.out.println("memberProduct = " + memberProduct.getOrderAmount());
	}
}