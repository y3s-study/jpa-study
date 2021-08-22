package jpabook.ch9.embedded.association;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneServiceProvider {
	@Id
	private String name;
}
