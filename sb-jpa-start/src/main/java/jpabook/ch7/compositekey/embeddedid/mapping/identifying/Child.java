package jpabook.ch7.compositekey.embeddedid.mapping.identifying;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Child {
	@EmbeddedId
	private ChildId id;

	@MapsId("parentId")
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public Parent parent;

	private String name;
}
