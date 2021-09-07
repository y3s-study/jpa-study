package jpabook.ch10.querydsl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Integer price;

	private Integer stockQuantity;
}
