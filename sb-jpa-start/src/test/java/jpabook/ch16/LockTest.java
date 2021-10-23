package jpabook.ch16;

import javax.persistence.EntityTransaction;

import jpabook.JpaTest;

public class LockTest extends JpaTest {
	void 버전_관리_사용() {
		// 트랜잭션 1조회 title="제목A", verison=1
		EntityTransaction tx = em.getTransaction();
		Board board = em.find(Board.class, 1L);

		// 트랜잭션 2에서 해당 게시물을 수정해서 title="제목c", version=2로 증가

		board.setTitle("제목B"); // 트랜잭션1 데이터 수정

		em.persist(board);
		tx.commit(); // 예외 발생, 데이터베이스 version=2, 엔티티 version=1
	}
}
