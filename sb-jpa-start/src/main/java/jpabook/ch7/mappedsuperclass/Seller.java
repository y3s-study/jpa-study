package jpabook.ch7.mappedsuperclass;

import javax.persistence.Entity;

@Entity
public class Seller extends BaseEntity {
	private String shopName;
}
