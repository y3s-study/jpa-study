package org.y3s;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.proxy.HibernateProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.y3s.domain.Book;
import org.y3s.domain.Item;
import org.y3s.domain.Member;
import org.y3s.domain.OrderItem;
import org.y3s.domain.visitor.PrintVisitor;
import org.y3s.domain.visitor.TitleVisitor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProxyTest {

	@PersistenceContext
	EntityManager em;

	@Test
	public void 영속성컨텍스트와_프록시() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, newMember.getId());
		Member findMember = em.find(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());
		System.out.println("findMember Type = " + findMember.getClass());

		assertTrue(refMember == findMember);
	}

	@Test
	public void 영속성컨텍스트와_프록시2() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member findMember = em.find(Member.class, newMember.getId());
		Member refMember = em.getReference(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());
		System.out.println("findMember Type = " + findMember.getClass());

		assertTrue(refMember == findMember);
	}

	@Test
	public void 프록시_타입비교() {
		Member newMember = new Member("회원1");
		em.persist(newMember);
		em.flush();
		em.clear();

		Member refMember = em.getReference(Member.class, newMember.getId());

		System.out.println("refMember Type = " + refMember.getClass());

		assertTrue(refMember instanceof Member);
	}

	@Test
	public void 프록시와_동등성비교() {
		Member saveMember = new Member("회원1");
		em.persist(saveMember);
		em.flush();
		em.clear();

		Member newMember = new Member("회원1");
		Member refMember = em.getReference(Member.class, saveMember.getId());

		assertTrue(newMember.equals(refMember));
	}

	@Test
	public void 부모타입으로_프록시조회() {
		Book saveBook = new Book();
		saveBook.setName("jpabook");
		saveBook.setAuthor("kim");
		em.persist(saveBook);

		em.flush();
		em.clear();

		Item proxyItem = em.getReference(Item.class, saveBook.getId());
		System.out.println("proxyItem = " + proxyItem.getClass());

		if (proxyItem instanceof Book) {
			System.out.println("proxyItem instanceof Book");
			Book book = (Book)proxyItem;
			System.out.println("책 저자 = " + book.getAuthor());
		}

		assertFalse(proxyItem.getClass() == Book.class);
		assertFalse(proxyItem instanceof Book);
		assertTrue(proxyItem instanceof Item);
	}

	@Test
	public void 상속관계와_프록시_도메인모델() {
		Book book = new Book();
		book.setName("jpabook");
		book.setAuthor("kim");
		em.persist(book);

		OrderItem saveOrderItem = new OrderItem();
		saveOrderItem.setItem(book);
		em.persist(saveOrderItem);

		em.flush();
		em.clear();

		OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
		Item item = orderItem.getItem();

		System.out.println("item.getClass() = " + item.getClass());

		assertFalse(item.getClass() == Book.class);
		assertFalse(item instanceof Book);
		assertTrue(item instanceof Item);
	}

	@Test
	public void 프록시_벗기기() {
		Book book = new Book();
		book.setName("jpabook");
		book.setAuthor("kim");
		em.persist(book);

		OrderItem saveOrderItem = new OrderItem();
		saveOrderItem.setItem(book);
		em.persist(saveOrderItem);

		em.flush();
		em.clear();

		OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
		Item item = orderItem.getItem();
		Item unProxyItem = unProxy(item);

		if (unProxyItem instanceof Book) {
			System.out.println("proxyItem instanceof Book");
			Book unProxyItemBook = (Book)unProxyItem;
			System.out.println("책 저자 = " + unProxyItemBook.getAuthor());
		}

		assertTrue(item != unProxyItem);
	}

	private static <T> T unProxy(Object entity) {
		if (entity instanceof HibernateProxy) {
			entity = ((HibernateProxy)entity)
				.getHibernateLazyInitializer()
				.getImplementation();
		}

		return (T)entity;
	}

	// @Test
	// public void 프록시_인터페이스_제공_사용() {
	// 	Book book = new Book();
	// 	book.setName("jpabook");
	// 	book.setAuthor("kim");
	// 	em.persist(book);
	//
	// 	OrderItem saveOrderItem = new OrderItem();
	// 	saveOrderItem.setItem(book);
	// 	em.persist(saveOrderItem);
	//
	// 	em.flush();
	// 	em.clear();
	//
	// 	OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
	// 	orderItem.printItem();
	// }

	@Test
	public void 상속관계와_프록시_VisitorPattern() {
		Book book = new Book();
		book.setName("jpabook");
		book.setAuthor("kim");
		em.persist(book);

		OrderItem saveOrderItem = new OrderItem();
		saveOrderItem.setItem(book);
		em.persist(saveOrderItem);

		em.flush();
		em.clear();

		OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
		Item item = orderItem.getItem();

		//PrintVisitor
		item.accept(new PrintVisitor());
	}

	@Test
	public void TitleVisitor_사용() {
		Book book = new Book();
		book.setName("jpabook");
		book.setAuthor("kim");
		em.persist(book);

		OrderItem saveOrderItem = new OrderItem();
		saveOrderItem.setItem(book);
		em.persist(saveOrderItem);

		em.flush();
		em.clear();

		OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
		Item item = orderItem.getItem();

		//TitleVisitor
		TitleVisitor titleVisitor = new TitleVisitor();
		item.accept(titleVisitor);

		String title = titleVisitor.getTitle();
		System.out.println("title = " + title);
	}
}
