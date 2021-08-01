package jpabook.ch6.onetoone.twoway.target;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Locker {
	@Id
	@GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;

	private String name;

	@OneToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
}
