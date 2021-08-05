package jpabook.chapter7.tableperclass;

import jpabook.chapter7.join.Item;

import javax.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
}
