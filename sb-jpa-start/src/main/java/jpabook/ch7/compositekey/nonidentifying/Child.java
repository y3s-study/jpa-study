package jpabook.ch7.compositekey.nonidentifying;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Child {
	@Id
	@GeneratedValue
	@Column(name = "CHILD_ID")
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Parent parent;
}
