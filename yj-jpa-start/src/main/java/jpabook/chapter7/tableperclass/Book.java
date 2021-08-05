package jpabook.chapter7.tableperclass;

import jpabook.chapter7.join.Item;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String author;
    private String isbn;
}
