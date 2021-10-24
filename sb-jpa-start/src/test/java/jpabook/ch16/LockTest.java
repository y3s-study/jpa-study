package jpabook.ch16;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import jpabook.JpaTest;

public class LockTest extends JpaTest {
	void 버전_관리_사용() {
		// 트랜잭션 1조회 title="제목A", version=1
		EntityTransaction tx = em.getTransaction();
		Board board = em.find(Board.class, 1L);

		// 트랜잭션 2에서 해당 게시물을 수정해서 title="제목c", version=2로 증가

		board.setTitle("제목B"); // 트랜잭션1 데이터 수정

		em.persist(board);
		tx.commit(); // 예외 발생, 데이터베이스 version=2, 엔티티 version=1
	}

	void OPTIMISTIC() {
		// 트랜잭션 1조회 title="제목A", version=1
		EntityTransaction tx = em.getTransaction();
		Board board = em.find(Board.class, 1L);

		// 트랜잭션2에서 해당 게시물을 수정해서 title="제목C", version=2로 증가

		//트랜잭션1 커밋 시점에 버전 정보 검증, 예외 발생
		//(데이터베이스 version=2, 엔티티 version=1)
		tx.commit();
	}

	void OPTIMISTIC_FORCE_INCREMENT() {
		// 트랜잭션1 조회 title="제목A", version=1
		EntityTransaction tx = em.getTransaction();
		Board board = em.find(Board.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

		//트랜잭션1 커밋 시점에 버전 강제 증가
		tx.commit();
	}

	void PESSIMISTIC_LOCK_TIMEOUT() {
		Map<String, Object> properties = new HashMap<>();

		//타임아웃 10초까지 대기설정
		properties.put("javax.persistence.lock.timeout", 10000);

		em.find(Board.class, 1L, LockModeType.PESSIMISTIC_WRITE, properties);
	}
}
