package jpabook.ch8.proxy;

import javax.persistence.Entity;

@Entity
public class Team {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
