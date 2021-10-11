package jpabook.ch14.orderby;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "MEMBER_NAME")
	private String username;

	@ManyToOne
	private Team team;

	public Member() {
	}

	public Member(String username) {
		this.username = username;
	}
}
