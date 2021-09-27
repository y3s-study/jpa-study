package jpabook.jpql;

import org.junit.jupiter.api.Test;

public class BulkTest extends JpaTest {
    @Test
    void bulkUpdate() {
        int count = em.createQuery("update Item i set i.price = i.price * 1.1")
                .executeUpdate();

        System.out.println(count);
    }
}
