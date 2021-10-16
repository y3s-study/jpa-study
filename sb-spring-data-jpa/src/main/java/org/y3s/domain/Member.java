package org.y3s.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	private String username;

	public Member(String username) {
		this.username = username;
	}
}
