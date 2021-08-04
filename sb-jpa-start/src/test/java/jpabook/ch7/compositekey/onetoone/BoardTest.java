package jpabook.ch7.compositekey.onetoone;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

class BoardTest {
	private EntityManager em;

	@Test
	void save() {
		Board board = new Board();
		board.setTitle("제목");
		em.persist(board);

		BoardDetail boardDetail = new BoardDetail();
		boardDetail.setContent("내용");
		boardDetail.setBoard(board);
		em.persist(boardDetail);
	}

}