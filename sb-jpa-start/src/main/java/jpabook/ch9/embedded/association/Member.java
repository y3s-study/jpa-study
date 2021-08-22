package jpabook.ch9.embedded.association;

import javax.persistence.Embedded;
import javax.persistence.Entity;



@Entity
public class Member {
	@Embedded
	private Address address;
	@Embedded
	private PhoneNumber phoneNumber;
}
