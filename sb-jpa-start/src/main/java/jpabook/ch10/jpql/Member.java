package jpabook.ch10.jpql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity(name = "Member")
public class Member {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String username;

	private Integer age;

	public static Member create(String username, Integer age) {
		return new Member(username, age);
	}

	private Member(String username, Integer age) {
		this.username = username;
		this.age = age;
	}
}
