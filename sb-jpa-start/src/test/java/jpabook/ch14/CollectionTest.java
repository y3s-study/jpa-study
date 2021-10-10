package jpabook.ch14;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;

import jpabook.JpaTest;
import jpabook.ch14.collection.Team;
import jpabook.ch14.list.Board;
import jpabook.ch14.list.Comment;

@Transactional
public class CollectionTest extends JpaTest {
	@Test
	void 컬렉션_테스트() {
		Team team = new Team();

		System.out.println("before persist = " + team.getMembers().getClass());
		em.persist(team);
		System.out.println("after persist = " + team.getMembers().getClass());
	}

	@Test
	void OrderColumn_테스트() {
		Board board = new Board("제목1", "내용1");
		em.persist(board);

		Comment comment1 = new Comment("댓글1");
		comment1.setBoard(board);
		board.getComments().add(comment1); //POSITION 0
		em.persist(comment1);

		Comment comment2 = new Comment("댓글2");
		comment2.setBoard(board);
		board.getComments().add(comment2); //POSITION 1
		em.persist(comment2);

		Comment comment3 = new Comment("댓글3");
		comment3.setBoard(board);
		board.getComments().add(comment3); //POSITION 2
		em.persist(comment3);

		Comment comment4 = new Comment("댓글4");
		comment4.setBoard(board);
		board.getComments().add(comment4); //POSITION 3
		em.persist(comment4);
	}
}
