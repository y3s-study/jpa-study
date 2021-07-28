package jpabook.chapter6.manytomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    private String name;
}
