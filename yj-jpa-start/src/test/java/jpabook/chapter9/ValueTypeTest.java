package jpabook.chapter9;

import jpabook.JpaTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTypeTest extends JpaTest {
    @Test
    void compare() {
        Address address1 = new Address("서울시", "종로구", "1번지");
        Address address2 = new Address("서울시", "종로구", "1번지");

        assertNotSame(address1, address2); // identity (==)
        assertEquals(address1, address2); // equivalence (equals())
    }

    @Test
    void save() {
        Member member = new Member();

        // 임베디드 값 타입
        member.setHomeAddress(new Address("김포시", "사우로", "12345"));

        // 기본값 타입 컬렉션
        member.getFavoriteFoods().add("피자");
        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("삼겹살");

        // 임베디드 값 타입 컬렉션
        member.getAddressHistory().add(new Address("서울시", "광진구", "111"));
        member.getAddressHistory().add(new Address("서울시", "관악구", "222"));

        em.persist(member);
        em.flush();
    }

    @Test
    void query() {
        Member member = new Member();

        member.setHomeAddress(new Address("김포시", "사우로", "12345"));

        member.getFavoriteFoods().add("피자");
        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("삼겹살");

        member.getAddressHistory().add(new Address("서울시", "광진구", "111"));
        member.getAddressHistory().add(new Address("서울시", "관악구", "222"));

        em.persist(member);
        em.flush();
        em.clear();

        System.out.println("------------------------------------------------");
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("------------------------------------------------");

        // no query
        System.out.println("getHomeAddress()");
        findMember.getHomeAddress();

        // no query
        System.out.println("getFavoriteFoods()");
        Set<String> favoriteFoods = findMember.getFavoriteFoods();

        // select query
        System.out.println("print out favoriteFoods");
        favoriteFoods.forEach(food -> System.out.println("favorite food : " + food));

        // no query
        System.out.println("getAddressHistory()");
        List<Address> addressHistory = findMember.getAddressHistory();

        // select query
        System.out.println("print out address");
        System.out.println(addressHistory.get(0));
    }

    @Test
    void update() {
        Member member = new Member();

        member.setHomeAddress(new Address("김포시", "사우로", "12345"));

        member.getFavoriteFoods().add("피자");
        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("삼겹살");

        member.getAddressHistory().add(new Address("서울시", "광진구", "111"));
        member.getAddressHistory().add(new Address("서울시", "광진구", "111"));
        member.getAddressHistory().add(new Address("서울시", "관악구", "222"));

        em.persist(member);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        // 임베디드 값 타입 수정
        findMember.setHomeAddress(new Address("신도시", "새로운길", "9999"));

        // 기본 값 타입 컬렉션 수정
        Set<String> favoriteFoods = findMember.getFavoriteFoods();
        favoriteFoods.remove("삼겹살");
        favoriteFoods.add("곱창");

        // 임베디드 값 타입 컬렉션 수정
        List<Address> addressHistory = findMember.getAddressHistory();
        addressHistory.remove(new Address("서울시", "광진구", "111"));
        addressHistory.add(new Address("신도시", "새로운길", "9999"));

        em.flush();
    }
}
