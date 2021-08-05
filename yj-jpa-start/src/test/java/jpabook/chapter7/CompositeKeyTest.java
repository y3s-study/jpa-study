package jpabook.chapter7;

import jpabook.JpaTest;
import jpabook.chapter7.compositekey.Parent;
import jpabook.chapter7.compositekey.ParentId;
import org.junit.jupiter.api.Test;

public class CompositeKeyTest extends JpaTest {
    @Test
    void save() {
        Parent parent = new Parent();
        parent.setId1("aaa");
        parent.setId2("bbb");
        parent.setName("parent name");
        em.persist(parent);

        em.flush();
    }

    @Test
    void query() {
        ParentId parentId = new ParentId("aaa", "bbb");
        em.find(Parent.class, parentId);
    }
}
