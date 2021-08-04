package jpabook.ch7.compositekey.idclass.mapping.identifiying;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ChildId.class)
public class Child {
	@Id
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Parent parent;

	@Id
	@Column(name = "CHILD_ID")
	private String childId;

	private String name;
}
