package jpabook.ch9.embedded.association;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {
	private String street;
	private String city;
	private String state;
	@Embedded
	private Zipcode zipcode;
}
