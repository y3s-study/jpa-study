package jpabook.ch14.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
	public class Team {
		@Id
		@GeneratedValue
		private Long id;

		@OneToMany
		@JoinColumn
		private Collection<Member> members;

	public Collection<Member> getMembers() {
		return members;
	}

	// org.hibernate.collection.internal.PersistentBag
	@OneToMany
	Collection<Member> collection = new ArrayList<>();

	// org.hibernate.collection.internal.PersistentBag
	@OneToMany
	List<Member> list = new ArrayList<>();

	// org.hibernate.collection.internal.PersistentSet
	@OneToMany
	Set<Member> set = new HashSet<>();

	// org.hibernate.collection.internal.PersistentList
	@OneToMany
	@OrderColumn
	List<Member> orderColumnList = new ArrayList<>();
}
