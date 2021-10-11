package jpabook.ch14.collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ListChild {
	@Id
	@GeneratedValue
	private Long id;
}
