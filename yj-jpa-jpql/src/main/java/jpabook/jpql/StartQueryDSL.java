package jpabook.jpql;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StartQueryDSL {
    public static void main(String[] args) {
        queryDsl();
    }

    private static void queryDsl() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember qMember = new QMember("m");

        queryFactory
                .selectFrom(qMember)
                .where(qMember.username.eq("회원1"))
                .orderBy(qMember.username.desc())
                .fetchOne();
    }
}
