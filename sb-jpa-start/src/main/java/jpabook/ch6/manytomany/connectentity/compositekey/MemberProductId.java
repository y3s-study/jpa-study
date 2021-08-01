package jpabook.ch6.manytomany.connectentity.compositekey;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable {

	private String member;	//MemberProduct.member와 연결
	private String product;	//MemberProduct.product와 연결

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MemberProductId that = (MemberProductId)o;
		return member.equals(that.member) && product.equals(that.product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(member, product);
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
}
