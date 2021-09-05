package jpabook.ch10.jpql;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@ManyToOne
	private Team team;

	@OneToMany
	private List<Order> orders;

	public static Member create(String username, Integer age) {
		return new Member(username, age);
	}

	private Member(String username, Integer age) {
		this.username = username;
		this.age = age;
	}
}
