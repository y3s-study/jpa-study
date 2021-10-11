package jpabook.ch14.converter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Convert(converter = BooleanToYNConverter.class, attributeName = "vip")
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private boolean vip;
}
