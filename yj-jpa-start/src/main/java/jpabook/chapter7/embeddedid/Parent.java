package jpabook.chapter7.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "Parent1")
@Getter
@Setter
public class Parent {
    @EmbeddedId
    private ParentId id;

    private String name;
}
