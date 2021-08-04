package jpabook.ch7.compositekey.idclass.mapping.identifiying;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
@IdClass(GrandChildId.class)
public class GrandChild {
	@Id
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID"),
		@JoinColumn(name = "CHILD_ID")
	})
	private Child child;

	private String name;
}
