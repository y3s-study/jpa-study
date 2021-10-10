 package jpabook.ch14.collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectionChild {
	@Id
	@GeneratedValue
	private Long id;
}
