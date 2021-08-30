package jpabook.ch10.jpql;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
import jpabook.ch6.manytomany.connectentity.primarykey.Product;

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

	@Test
	void typeQuery() {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);

		List<Member> resultList = query.getResultList();
		for (Member m : resultList) {
			System.out.println("member = " + m);
		}
	}

	@Test
	void query() {
		Query query = em.createQuery("SELECT m.username, m.age from Member m");
		List resultList = query.getResultList();
		for (Object o :
			resultList) {
			Object[] result = (Object[])o;
			System.out.println("username = " + result[0]);
			System.out.println("age = " + result[1]);
		}
	}

	@Test
	void named_parameters() {
		String usernameParam = "User1";

		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);

		query.setParameter("username", usernameParam);
		List<Member> resultList = query.getResultList();
	}

	@Test
	void positional_parameters() {
		String usernameParam = "User1";

		List<Member> members = em.createQuery("SELECT m FROM Member m where m.username = ?1", Member.class)
			.setParameter(1, usernameParam)
			.getResultList();
	}

	@Test
	void projection() {
		Query query = em.createQuery("SELECT m.username, m.age, FROM Member m");
		List resultList = query.getResultList();

		Iterator iterator = resultList.iterator();
		while (iterator.hasNext()) {
			Object[] row = (Object[])iterator.next();
			String username = (String)row[0];
			Integer age = (Integer)row[1];
		}
	}

	@Test
	void projection_by_object_array() {
		List<Object[]> resultList = em.createQuery("SELECT m.username, m.age FROM Member m")
			.getResultList();

		for (Object[] row : resultList) {
			String username = (String)row[0];
			Integer age = (Integer)row[1];
		}
	}

	@Test
	void projection_by_entity() {
		List<Object[]> resultList = em.createQuery("SELECT o.member, o.product, o.orderAmount FROM Order o")
			.getResultList();

		for (Object[] row : resultList) {
			Member member = (Member)row[0];
			Product product = (Product)row[1];
			Integer orderAmount = (Integer)row[2];
		}
	}

	@Test
	void new_명령어_사용전() {
		List<Object[]> resultList = em.createQuery("SELECT m.username, m.age FROM Member m")
			.getResultList();

		List<UserDto> userDtos = new ArrayList<>();
		for(Object[] row : resultList) {
			UserDto userDto = new UserDto((String)row[0], (Integer)row[1]);
			userDtos.add(userDto);
		}
	}

	@Test
	void new_명령어_사용후() {
		TypedQuery<UserDto> query = em.createQuery(
			"SELECT new jpabook.ch10.jpql.UserDto(m.username, m.age) FROM Member m", UserDto.class);

		List<UserDto> resultList = query.getResultList();
	}
}
