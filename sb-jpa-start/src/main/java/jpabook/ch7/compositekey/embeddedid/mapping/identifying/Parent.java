package jpabook.ch7.compositekey.embeddedid.mapping.identifying;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parent {
	@Id
	@Column(name = "PARENT_ID")
	private String id;

	private String name;
}
