package jpabook.model.entity.item;

import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
