package jpabook.ch15;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int price;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
}
