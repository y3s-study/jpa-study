package jpabook.ch7.compositekey.nonidentifying;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parent {
	@Id
	@GeneratedValue
	@Column(name = "PARENT_ID")
	private Long id;
	private String name;
}
