package jpabook.jpql.querydsl;

import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import jpabook.jpql.Item;
import jpabook.jpql.QItem;
import jpabook.jpql.dto.MemberDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static jpabook.jpql.QItem.item;
import static jpabook.jpql.QMember.member;
import static jpabook.jpql.QTeam.team;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleQueryTest extends QuerydslTest {
    @Test
    void searchCondition() {
        jpaQueryFactory
                .select(item)
                .from(item)
                .where(item.name.eq("좋은상품").and(item.price.gt(20000)))
                .fetch();
    }

    @Test
    void fetchOne_null() {
        Item result = jpaQueryFactory.selectFrom(item).fetchOne();
        assertNull(result);
    }

    @Test
    void fetchOne_One() {
        em.persist(new Item());

        Item result = jpaQueryFactory.selectFrom(item).fetchOne();
        assertNotNull(result);
    }

    @Test
    void fetchOne_Two() {
        em.persist(new Item());
        em.persist(new Item());

        assertThrows(NonUniqueResultException.class, () ->  jpaQueryFactory.selectFrom(item).fetchOne());
    }

    @Test
    void paging_order() {
        jpaQueryFactory.selectFrom(item)
                .orderBy(item.price.desc(), item.stockQuantity.asc())
                .offset(10).limit(20)
                .fetch();
    }

    @Test
    void paging_order_result() {
        jpaQueryFactory.selectFrom(item)
                .orderBy(item.price.desc(), item.stockQuantity.asc())
                .offset(10).limit(20)
                .fetch();

    }

    @Test
    void group() {
        jpaQueryFactory.selectFrom(item)
                .groupBy(item.price)
                .having(item.price.gt(1000))
                .fetch();
    }

    @Test
    void basicJoin() {
        jpaQueryFactory.selectFrom(member)
                .join(member.team)
                .fetch();

        jpaQueryFactory.selectFrom(member)
                .join(member.team, team)
                .fetch();
    }

    @Test
    void joinOn() {
        jpaQueryFactory.selectFrom(member)
                .join(member.team, team)
                .on(team.name.eq("팀"))
                .fetch();
    }

    @Test
    void fetchJoin() {
        jpaQueryFactory.selectFrom(member)
                .innerJoin(member.team).fetchJoin()
                .fetch();
    }

    @Test
    void thetaJoin() {
        jpaQueryFactory.select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
    }

    @Test
    void subQuery_single() {
        QItem itemSub = new QItem("itemSub");

        jpaQueryFactory.selectFrom(item)
                .where(item.price.eq(
                        JPAExpressions.select(itemSub.price.max()).from(itemSub)
                ))
                .fetch();
    }

    @Test
    void subQuery_multi() {
        QItem itemSub = new QItem("itemSub");

        jpaQueryFactory.selectFrom(item)
                .where(item.in(
                        JPAExpressions.selectFrom(itemSub).where(item.name.eq(itemSub.name))
                ))
                .fetch();
    }

    @Test
    void singleValueProjectionTest() {
        List<String> usernames = jpaQueryFactory.select(member.username)
                .from(member)
                .fetch();
    }

    @Test
    void multiProjectionTest() {
        List<Tuple> results = jpaQueryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();

        for (Tuple tuple : results) {
            System.out.println("username = " + tuple.get(member.username));
            System.out.println("age = " + tuple.get(member.age));
        }
    }

    @Test
    void beanProjection() {
        List<MemberDto> memberDtos = jpaQueryFactory
                .select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
    }

    @Test
    void batchUpdate() {
        jpaQueryFactory
                .update(member)
                .set(member.username, "useruser")
                .set(member.age, member.age.add(10))
                .execute();
    }

    @Test
    void batchDelete() {
        jpaQueryFactory.delete(member)
                .where(member.username.eq("name"))
                .execute();

        jpaQueryFactory.insert(member)
                .set(member.age, 10)
                .execute();
    }
}
