package jpabook.ch7.compositekey.idclass;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Child {
	@Id
	private String id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PARENT_ID1",
			referencedColumnName = "PARENT_ID1"),
		@JoinColumn(name = "PARENT_ID2",
			referencedColumnName = "PARENT_ID2")
	})
	private Parent parent;
}
