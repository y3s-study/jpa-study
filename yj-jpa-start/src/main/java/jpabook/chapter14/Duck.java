package jpabook.chapter14;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@EntityListeners(DuckListener.class)
public class Duck {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @PrePersist
    public void prePersist() {
        System.out.println("prePersist id = " + id);
    }

    @PostPersist
    public void postPersist() {
        System.out.println("postPersist id = " + id);
    }

    @PostLoad
    public void postLoad() {
        System.out.println("postLoad");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("preRemove");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("postRemove");
    }
}
