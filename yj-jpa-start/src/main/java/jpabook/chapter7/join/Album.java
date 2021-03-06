package jpabook.chapter7.join;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "A")
@Getter
@Setter
@ToString
public class Album extends Item {
    private String artist;
}
