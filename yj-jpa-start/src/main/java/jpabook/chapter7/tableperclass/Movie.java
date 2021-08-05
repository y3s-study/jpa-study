package jpabook.chapter7.tableperclass;

import jpabook.chapter7.join.Item;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}
