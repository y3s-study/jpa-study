package jpabook.ch14.set;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Parent {
	@OneToMany
	@JoinColumn
	private Set<SetChild> set = new HashSet<>();
}
