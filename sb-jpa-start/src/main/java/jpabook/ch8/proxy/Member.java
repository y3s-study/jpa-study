package jpabook.ch8.proxy;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Member {

	private String username;

	@ManyToOne
	private Team team;

	public String getUsername() {
		return username;
	}

	public Team getTeam() {
		return team;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
