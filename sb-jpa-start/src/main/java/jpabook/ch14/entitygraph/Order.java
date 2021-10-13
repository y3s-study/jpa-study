package jpabook.ch14.entitygraph;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import jpabook.ch14.collection.Member;

@NamedEntityGraph(name = "Order.withMember", attributeNodes = {
	@NamedAttributeNode("member")
})
@Entity
@Table(name = "ORDERS")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
}
