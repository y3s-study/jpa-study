package jpabook.jpql.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpql.JpaTest;
import org.junit.jupiter.api.BeforeEach;

public class QuerydslTest extends JpaTest {
    protected JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    protected void beforeEach() {
        super.beforeEach();
        jpaQueryFactory = new JPAQueryFactory(em);
    }

}
