package jpabook.ch9.embedded.association;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
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
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
		@AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
		@AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE")),
		@AttributeOverride(name = "zipcode.zip", column = @Column(name = "COMPANY_ZIPCODE_ZIP")),
		@AttributeOverride(name = "zipcode.plusFour", column = @Column(name = "COMPANY_ZIPCODE_PLUS_FOUR"))
	})
	private Address companyAddress;

	@Embedded
	private PhoneNumber phoneNumber;
}
