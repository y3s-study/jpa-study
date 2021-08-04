package jpabook.ch7.compositekey.idclass.mapping.identifiying;

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
