package jpabook.ch10.jpql;

import java.util.List;

import javax.persistence.Entity;

import lombok.Getter;

@Getter
@Entity
public class Team {
	private String name;

	private List<Member> members;
}
