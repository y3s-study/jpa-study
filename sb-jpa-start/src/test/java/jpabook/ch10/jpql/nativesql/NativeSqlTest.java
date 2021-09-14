package jpabook.ch10.jpql.nativesql;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;
import jpabook.ch10.jpql.Member;
import jpabook.ch6.manytomany.connectentity.compositekey.Product;

public class NativeSqlTest extends JpaTest {
	@Test
	void 엔티티_조회() {
		String sql = "SELECT ID, AGE, NAME, TEAM_ID " +
			"FROM MEMBER WHERE AGE > ?";

		Query nativeQuery = em.createNativeQuery(sql, Member.class)
			.setParameter(1, 20);

		List<Member> resultList = nativeQuery.getResultList();
	}

	@Test
	void 값_조회() {
		String sql = "SELECT ID, AGE, NAME, TEAM_ID " +
			"FROM MEMBER WHERE AGE > ?";

		Query nativeQuery = em.createNativeQuery(sql)
			.setParameter(1, 10);

		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			System.out.println("id = " + row[0]);
			System.out.println("age = " + row[1]);
			System.out.println("name = " + row[2]);
			System.out.println("team_id = " + row[3]);
		}
	}

	@Test
	void 결과_매핑_사용() {
		String sql =
			"SELECT M.ID, AGE, NAME, TEAM_ID, I.ORDER_COUNT " +
				"FROM MEMBER M " +
				"LEFT JOIN " +
				"   (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
				"   FROM ORDERS O, MEMBER IM " +
				"   WHERE O.MEMBER_ID = IM.ID) I " +
				"ON M.ID = I.ID";

		Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");
		List<Object[]> resultList = nativeQuery.getResultList();
		for (Object[] row : resultList) {
			Member member = (Member)row[0];
			BigInteger orderCount = (BigInteger)row[1];

			System.out.println("member = " + member);
			System.out.println("orderCount = " + orderCount);
		}
	}

	@Test
	void 표준_명세() {
		em.createNativeQuery(
			"SELECT o.id AS order_id, " +
				"o.quantity AS order_quantity, " +
				"o.item AS order_item, " +
				"i.name AS item_name, " +
				"FROM Order o, Item i " +
				"WHERE (order_quanity > 25) AND "
				+ "(order_item = i.id)", "OrderResults");
	}

	@Test
	void 네이티브_SQL_페이징() {
		String sql = "SELECT ID, AGE, NAME, TEAM_ID FROM MEMBER";
		Query nativeQuery = em.createNativeQuery(sql, Member.class)
			.setFirstResult(10)
			.setMaxResults(20);
	}

	@Test
	void 스토어드_프로시저_순서_기반_파라미터() {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");

		spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter(2, Integer.class, ParameterMode.OUT);

		spq.setParameter(1, 100);
		spq.execute();

		Integer out = (Integer)spq.getOutputParameterValue(2);
		System.out.println("out = " + out);
	}

	@Test
	void 스토어드_프로시저_파라미터_이름_사용() {
		StoredProcedureQuery spq = em.createStoredProcedureQuery("proc_multiply");
		spq.registerStoredProcedureParameter("inParam", Integer.class, ParameterMode.IN);
		spq.registerStoredProcedureParameter("outParam", Integer.class, ParameterMode.OUT);

		spq.setParameter("inParam", 100);
		spq.execute();

		Integer out = (Integer)spq.getOutputParameterValue("outParam");
		System.out.println("out = " + out);
	}

	@Test
	void Named_스토어드_프로시저_사용() {
		StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("multiply");

		spq.setParameter("inParam", 100);
		spq.execute();

		Integer out = (Integer)spq.getOutputParameterValue("outParam");
		System.out.println("out = " + out);
	}

	@Test
	void update_벌크_연산() {
		String qlString = "update Product p " +
			"set p.price = p.price * 1.1 " +
			"where p.stockAmount < :stockAmount ";

		int resultCount = em.createQuery(qlString)
			.setParameter("stockAmount", 10)
			.executeUpdate();
	}

	@Test
	void delete_벌크_연산() {
		String qlString = "delete from Product p " +
			"where p.price < :price";

		int resultCount = em.createQuery(qlString)
			.setParameter("price", 100)
			.executeUpdate();
	}

	@Test
	void insert_벌크_연산() {
		String qlString = "insert into ProductTemp(id, name, price, stockAmount) " +
			"select p.id, p.name, p.price, p.stockAmount from Product p " +
			"where p.price < :price";

		int resultCount = em.createQuery(qlString)
			.setParameter("price", 100)
			.executeUpdate();
	}

	@Test
	void 벌크_연산_주의점() {
		// 상품A 조회(상품A의 가격은 1000원이다.)
		Product productA = em.createQuery("select p from Product p where p.name = :name", Product.class)
			.setParameter("name", "productA")
			.getSingleResult();

		//출력결과: 1000
		System.out.println("productA 수정 전 = " + productA.getPrice());

		em.createQuery("update Product p set p.price = p.price * 1.1")
			.executeUpdate();

		//출력결과: 1000
		System.out.println("productA 수정 후 = " + productA.getPrice());
	}
}
