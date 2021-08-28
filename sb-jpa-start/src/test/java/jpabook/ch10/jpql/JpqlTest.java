package jpabook.ch10.jpql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.JpaTest;

class JpqlTest extends JpaTest {

	@BeforeEach
	void setup() {
		Member member = Member.create("kim", 20);
		em.persist(member);
		em.flush();
		em.clear();
	}

	@Test
	void jpql_사용() {
		String jpql = "select m from Member as m where m.username = 'kim'";
		List<Member> resultList = em.createQuery(jpql, Member.class).getResultList();
		assertAll(
			() -> assertEquals(1, resultList.size()),
			() -> assertEquals("kim", resultList.get(0).getUsername())
		);
	}

	@Test
	void criteria_사용() {
		// Criteria 사용 준비
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> query = cb.createQuery(Member.class);

		// 루트 클래스 (조회를 시작할 클래스)
		Root<Member> m = query.from(Member.class);

		// 쿼리 생성
		CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
		List<Member> resultList = em.createQuery(cq).getResultList();

		assertAll(
			() -> assertEquals(1, resultList.size()),
			() -> assertEquals("kim", resultList.get(0).getUsername())
		);
	}

	@Test
	void querydsl_사용() {
		// 준비
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QMember member = QMember.member;

		// 쿼리, 결과조회
		List<Member> resultList = queryFactory.selectFrom(member)
			.where(member.username.eq("kim"))
			.fetch();

		assertAll(
			() -> assertEquals(1, resultList.size()),
			() -> assertEquals("kim", resultList.get(0).getUsername())
		);
	}

	@Test
	void nativeQuery_사용() {
		String sql = "SELECT ID, AGE, NAME FROM MEMBER WHERE NAME = 'kim'";
		List<Member> resultList = em.createNativeQuery(sql, Member.class).getResultList();

		assertAll(
			() -> assertEquals(1, resultList.size()),
			() -> assertEquals("kim", resultList.get(0).getUsername())
		);
	}

	@Test
	void 하이버네이트_jdbc() {
		Session session = em.unwrap(Session.class);
		session.doWork(connection -> {
			//;
		});
	}
}