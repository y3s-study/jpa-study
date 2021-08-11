package jpabook.ch8.proxy;

import javax.persistence.EntityManager;

class ProxyTest {
	private EntityManager em;

	public void printUserAndTeam(String memberId) {
		Member member = em.find(Member.class, memberId);
		Team team = member.getTeam();
		System.out.println("회원이름: " + member.getUsername());
		System.out.println("소속팀: " + team.getName());
	}

	public void printUser(String memberId) {
		Member member = em.find(Member.class, memberId);
		System.out.println("회원이름: " + member.getUsername());
	}

}