package jpabook.ch6.manytomany.connectentity.compositekey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(MemberProductId.class)
public class MemberProduct {

	@Id
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member; // MemberProductId.member와 연결

	@Id
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product; // MemberProductId.product와 연결

	private int orderAmount;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
}
