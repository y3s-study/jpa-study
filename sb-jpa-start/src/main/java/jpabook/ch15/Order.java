package jpabook.ch15;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Member member;
}
