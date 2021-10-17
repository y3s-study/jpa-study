package org.y3s.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof Member))
			return false;
		Member member = (Member)o;
		return Objects.equals(username, member.getUsername());
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
}
