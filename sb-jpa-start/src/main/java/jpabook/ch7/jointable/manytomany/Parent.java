package jpabook.ch7.jointable.manytomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Parent {
	@Id
	@GeneratedValue
	@Column(name = "PARENT_ID")
	private Long id;
	private String name;

	@ManyToMany
	@JoinTable(name = "PARENT_CHILD",
		joinColumns = @JoinColumn(name = "PARENT_ID"),
		inverseJoinColumns = @JoinColumn(name = "CHILD_ID")
	)
	private List<Child> child = new ArrayList<>();
}
