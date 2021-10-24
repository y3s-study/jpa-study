package jpabook.ch16.cache;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Cacheable
@Entity
public class Member {
	@Id
	@GeneratedValue
	private Long id;
}
