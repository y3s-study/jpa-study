package jpabook.ch10.jpql;

import static jpabook.ch10.jpql.QMember.*;

import java.util.List;

import org.hibernate.criterion.Projection;
import org.junit.jupiter.api.Test;

import com.google.common.base.Strings;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jpabook.JpaTest;
import jpabook.ch10.querydsl.Item;
import jpabook.ch10.querydsl.ItemDto;
import jpabook.ch10.querydsl.QItem;
import jpabook.ch10.querydsl.QOrderItem;
import jpabook.ch10.querydsl.SearchParam;

public class QueryDSLTest extends JpaTest {
	@Test
	void query_dsl_시작() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QMember qMember = new QMember("m");
		List<Member> members = queryFactory.selectFrom(qMember)
			.where(qMember.username.eq("회원1"))
			.orderBy(qMember.username.desc())
			.fetch();
	}

	@Test
	void import_static_활용() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		List<Member> members = queryFactory.selectFrom(member)
			.where(member.username.eq("회원1"))
			.orderBy(member.username.desc())
			.fetch();
	}

	@Test
	void query_dsl_기본_쿼리_기능() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		List<Item> list = queryFactory.selectFrom(item)
			.where(item.name.eq("좋은상품").and(item.price.gt(20000)))
			.fetch();
	}

	@Test
	void 페이징_정렬() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		QueryResults<Item> result = queryFactory.selectFrom(item)
			.where(item.price.gt(20000))
			.orderBy(item.price.desc(), item.stockQuantity.asc())
			.offset(10)
			.limit(20)
			.fetchResults();

		long total = result.getTotal();
		long limit = result.getLimit();
		long offset = result.getOffset();
		List<Item> results = result.getResults();
	}

	@Test
	void groupBy() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		queryFactory.selectFrom(item)
			.groupBy(item.price)
			.having(item.price.gt(1000))
			.fetch();
	}

	@Test
	void join() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		QOrderItem orderItem = QOrderItem.orderItem;

		queryFactory.selectFrom(order)
			.join(order.member, member)
			// .leftJoin(order.orderItems, orderItem)
			.fetch();
	}

	@Test
	void join_on() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QOrderItem orderItem = QOrderItem.orderItem;

		queryFactory.selectFrom(order)
			.leftJoin(order.orderItems, orderItem)
			.on(orderItem.count().gt(2))
			.fetch();
	}

	@Test
	void fetch_join() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		QOrderItem orderItem = QOrderItem.orderItem;

		queryFactory.selectFrom(order)
			.innerJoin(order.member, member)
			.leftJoin(order.orderItems, orderItem).fetchJoin()
			.fetch();
	}

	@Test
	void theta_join() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;

		queryFactory.select(order, member)
			.from(order, member)
			.where(order.member.eq(member))
			.fetch();
	}

	@Test
	void subquery_한_건() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		QItem itemSub = new QItem("itemSub");

		queryFactory.selectFrom(item)
			.where(item.price.eq(
				JPAExpressions.select(itemSub.price.max()).from(itemSub))
			)
			.fetchOne();
	}

	@Test
	void subquery_여러_건() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		QItem itemSub = new QItem("itemSub");

		queryFactory.selectFrom(item)
			.where(item.in(
				JPAExpressions.selectFrom(itemSub)
					.where(item.name.eq(itemSub.name))
				)
			)
			.fetch();
	}

	@Test
	void 프로젝션_대상이_하나() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		List<String> result = queryFactory.select(item.name)
			.from(item)
			.fetch();

		for (String name : result) {
			System.out.println("name = " + name);
		}
	}

	@Test
	void 튜플_사용() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		List<Tuple> result = queryFactory.select(item.name, item.price)
			.from(item)
			.fetch();

		for (Tuple tuple : result) {
			System.out.println("name = " + tuple.get(item.name));
			System.out.println("price = " + tuple.get(item.price));
		}
	}

	@Test
	void 프로퍼티_접근() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		List<ItemDto> result = queryFactory.select(
			Projections.bean(ItemDto.class, item.name.as("username"), item.price))
			.from(item)
			.fetch();
	}

	@Test
	void 필드_직접_접근() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		List<ItemDto> result = queryFactory.select(
			Projections.fields(ItemDto.class, item.name.as("username"), item.price))
			.from(item)
			.fetch();
	}

	@Test
	void 생성자_사용() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;
		List<ItemDto> result = queryFactory.select(
			Projections.constructor(ItemDto.class, item.name, item.price))
			.from(item)
			.fetch();
	}

	@Test
	void distinct() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		List<Item> result = queryFactory.selectFrom(item)
			.distinct()
			.fetch();
	}

	@Test
	void 수정_배치_쿼리() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		long count = queryFactory.update(item)
			.where(item.name.eq("jpa"))
			.set(item.price, item.price.add(100))
			.execute();
	}

	@Test
	void 삭제_배치_쿼리() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		long count = queryFactory.delete(item)
			.where(item.name.eq("jpa"))
			.execute();
	}

	@Test
	void 동적_쿼리() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		SearchParam param = new SearchParam();
		param.setName("이름");
		param.setPrice(10000);

		BooleanBuilder builder = new BooleanBuilder();
		if (!Strings.isNullOrEmpty(param.getName())) {
			builder.and(item.name.contains(param.getName()));
		}
		if (param.getPrice() != null) {
			builder.and(item.price.gt(param.getPrice()));
		}

		List<Item> result = queryFactory.selectFrom(item)
			.where(builder)
			.fetch();
	}

	@Test
	void 메소드_위임() {
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QItem item = QItem.item;

		List<Item> result = queryFactory.selectFrom(item)
			.where(item.isExpensive(30000))
			.fetch();
	}
}
