package jpabook.ch9.embedded.association;

import javax.persistence.Embeddable;

@Embeddable
public class Zipcode {
	private String zip;
	private String plusFour;
}
