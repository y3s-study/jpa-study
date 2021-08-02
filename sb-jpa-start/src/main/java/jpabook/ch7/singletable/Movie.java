package jpabook.ch7.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.bytebuddy.build.ToStringPlugin;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
}
