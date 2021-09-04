package jpabook.jpql.querydsl;

import org.junit.jupiter.api.Test;

import static jpabook.jpql.QItem.item;

public class SimpleQueryTest extends QuerydslTest {
    @Test
    void searchCondition() {
        jpaQueryFactory
                .select(item)
                .from(item)
                .where(item.name.eq("좋은상품").and(item.price.gt(20000)))
                .fetch();
    }
}
