package jpabook.chapter14;

import javax.persistence.*;

public class DuckListener {
    @PrePersist
    public void prePersist(Duck duck) {
        System.out.println("prePersist id = " + duck.getId());
    }

    @PostPersist
    public void postPersist(Duck duck) {
        System.out.println("postPersist id = " + duck.getId());
    }
}
