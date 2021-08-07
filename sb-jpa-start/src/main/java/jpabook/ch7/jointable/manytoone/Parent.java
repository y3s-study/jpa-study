package jpabook.ch7.jointable.manytoone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parent {
	@Id
	@GeneratedValue
	@Column(name = "PARENT_ID")
	private Long id;
	private String name;

	@OneToMany(mappedBy = "parent")
	private List<Child> child = new ArrayList<>();
}
