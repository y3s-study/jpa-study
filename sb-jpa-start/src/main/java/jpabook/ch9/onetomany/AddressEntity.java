package jpabook.ch9.onetomany;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import jpabook.ch9.embedded.collection.Address;

@Entity
public class AddressEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Embedded
	private Address address;
}
