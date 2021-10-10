package jpabook.ch14.list;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	private String comment;

	@ManyToOne
	@JoinColumn(name = "BOARD_ID")
	private Board board;

	public Comment(String comment) {
		this.comment = comment;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
