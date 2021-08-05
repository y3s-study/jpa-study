package jpabook.chapter7.superclass;

import javax.persistence.Entity;

@Entity
public class Seller extends BaseEntity {
    private String shopName;
}
