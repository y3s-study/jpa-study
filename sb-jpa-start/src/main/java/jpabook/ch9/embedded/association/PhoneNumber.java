package jpabook.ch9.embedded.association;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
	private String areaCode;
	private String localNumber;

	@ManyToOne
	private PhoneServiceProvider provider;
}
