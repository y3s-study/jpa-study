package jpabook.chapter7.singletable;

import jpabook.chapter7.join.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "A")
public class Album extends Item {
    private String artist;
}
