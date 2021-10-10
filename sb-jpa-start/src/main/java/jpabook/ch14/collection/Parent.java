package jpabook.ch14.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Parent {
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany
	@JoinColumn
	private Collection<CollectionChild> collection = new ArrayList<>();

	@OneToMany
	@JoinColumn
	private List<ListChild> list = new ArrayList<>();
}
