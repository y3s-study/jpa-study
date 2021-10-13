package jpabook.ch14.entitygraph;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "MEMBER_NAME")
	private String username;
}
