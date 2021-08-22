package jpabook.ch9.embedded.immutable;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;

	protected Address() {}

	public Address(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}
}
