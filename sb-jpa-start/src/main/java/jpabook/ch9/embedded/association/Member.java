package jpabook.ch9.embedded.association;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Embedded
	private Address address;
	@Embedded
	private PhoneNumber phoneNumber;
}
