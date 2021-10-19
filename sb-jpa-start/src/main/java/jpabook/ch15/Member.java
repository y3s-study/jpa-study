package jpabook.ch15;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

@Entity
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	@BatchSize(size = 5)
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
	private List<Order> orders = new ArrayList<>();
}
