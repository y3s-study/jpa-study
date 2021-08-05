package jpabook.chapter7;

import jpabook.JpaTest;
import jpabook.chapter7.embeddedid.Parent;
import jpabook.chapter7.embeddedid.ParentId;
import org.junit.jupiter.api.Test;

public class EmbeddedIdTest extends JpaTest {
    @Test
    void save() {
        Parent parent = new Parent();
        ParentId id = new ParentId("aaa", "bbb");
        parent.setId(id);
        parent.setName("parent name");
        em.persist(parent);

        em.flush();
    }

    @Test
    void query() {
        ParentId id = new ParentId("aaa", "bbb");
        Parent parent = em.find(Parent.class, id);
        System.out.println(parent);
    }
}
