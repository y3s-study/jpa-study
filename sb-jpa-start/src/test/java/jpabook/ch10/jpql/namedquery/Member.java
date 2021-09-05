package jpabook.ch10.jpql.namedquery;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "Member.findByUsername",
		query = "select m from Member m where m.username = :username"),
	@NamedQuery(
		name = "Member.count",
		query = "select count(m) from Member m")
})
public class Member {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
}