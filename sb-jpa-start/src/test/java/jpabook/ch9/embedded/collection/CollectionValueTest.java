package jpabook.ch9.embedded.collection;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;

class CollectionValueTest extends JpaTest {

	@Test
	void 값_타잆_컬렉션_사용() {
		Member member = new Member();

		// 임베디드 값 타입
		member.setHomeAddress(new Address("통영", "몽돌해수욕장", "660-123"));

		// 기본값 타입 컬렉션
		member.getFavoriteFoods().add("짬뽕");
		member.getFavoriteFoods().add("짜장");
		member.getFavoriteFoods().add("탕수육");

		// 임베디드 값 타입 컬렉션
		member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
		member.getAddressHistory().add(new Address("서울", "강북", "000-000"));

		em.persist(member);
		em.flush();
		em.clear();
	}

	@Test
	void 지연로딩_테스트() {
		값_타잆_컬렉션_사용();

		// 1. member
		Member member = em.find(Member.class, 1L);

		// 2. member.homeAddress
		Address homeAddress = member.getHomeAddress();

		// 3. member.favoriteFoods
		Set<String> favoriteFoods = member.getFavoriteFoods();

		for (String favoriteFood : favoriteFoods) {
			System.out.println("favoriteFood = " + favoriteFoods);
		}

		// 4. member.addressHistory
		List<Address> addressHistory = member.getAddressHistory();

		addressHistory.get(0);
	}

	@Test
	void 수정() {
		값_타잆_컬렉션_사용();

		Member member = em.find(Member.class, 1L);

		// 1. 임베디드 값 타입 수정
		member.setHomeAddress(new Address("새로운도시", "신도시1", "123456"));

		// 2. 기본값 타입 컬렉션 수정
		Set<String> favoriteFoods = member.getFavoriteFoods();
		favoriteFoods.remove("탕수육");
		favoriteFoods.add("치킨");

		// 3. 임베디드 값 타입 컬렉션 수정
		List<Address> addressHistory = member.getAddressHistory();
		addressHistory.remove(new Address("서울", "강남", "123-123"));
		addressHistory.add(new Address("새로운도시", "새로운 주소", "123-456"));

		em.flush();
		em.clear();
	}
}