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
		for (Object[] row : resultList) {
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

	@Test
	void 페이징_사용() {
		TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC",
			Member.class);

		query.setFirstResult(10);
		query.setMaxResults(20);
		query.getResultList();
	}

	@Test
	void 내부조인() {
		String teamName = "팀A";
		String query = "SELECT m FROM Member m INNER JOIN m.team t "
			+ "WHERE t.name = :teamName";

		List<Member> members = em.createQuery(query, Member.class)
			.setParameter("teamName", teamName)
			.getResultList();
	}

	@Test
	void 페치조인() {
		String jpql = "select m from Member m join fetch m.team";

		List<Member> members = em.createQuery(jpql, Member.class)
			.getResultList();

		for (Member member : members) {
			// 페치 조인으로 회원과 팀을 함께 조회해서 지연로딩 발생 안함
			System.out.println("username = " + member.getUsername() + ", " +
				"teamname = " + member.getTeam().getName());
		}
	}

	@Test
	void 컬렉션_페치조인() {
		String jpql = "select t from Team t join fetch t.members where t.name = '팀A'";
		List<Team> teams = em.createQuery(jpql, Team.class).getResultList();

		for (Team team : teams) {
			System.out.println("teamname = " + team.getName() + ", team = " + team);
			for (Member member : team.getMembers()) {
				System.out.println("->username = " + member.getUsername() + ", member = " + member);
			}
		}
	}

	@Test
	void 엔티티를_파라미터로_직접_받는_코드() {

		String qlString = "select m from Member m where m = :member";
		List resultList = em.createQuery(qlString)
			.setParameter("member", Member.class)
			.getResultList();
	}

	@Test
	void 식별자_값을_직접_사용하는_코드() {

		String qlString = "select m from Member m where m.id = :memberId";
		List resultList = em.createQuery(qlString)
			.setParameter("memberId", 4L)
			.getResultList();
	}

	@Test
	void 외래키_대신에_엔티티를_직접_사용하는_코드() {
		Team team = em.find(Team.class, 1L);
		String qlString = "select m from Member m where m.team = :team";
		List resultList = em.createQuery(qlString)
			.setParameter("team", Team.class)
			.getResultList();
	}

	@Test
	void 외래키_식별자를_직접_사용하는_코드() {
		String qlString = "select m from Member m where m.team.id = :teamId";
		List resultList = em.createQuery(qlString)
			.setParameter("teamId", 1L)
			.getResultList();
	}

	@Test
	void named_query_사용() {
		List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
			.setParameter("username", "회원1")
			.getResultList();
	}

}
