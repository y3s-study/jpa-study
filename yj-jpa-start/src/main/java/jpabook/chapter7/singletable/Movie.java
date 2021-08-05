package jpabook.chapter7.singletable;

import jpabook.chapter7.join.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "M")
public class Movie extends Item {
    private String director;
    private String actor;
}
