package jpabook.ch7.compositekey.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Board {
	@Id
	@GeneratedValue
	@Column(name = "BOARD_ID")
	private Long id;

	private String title;

	@OneToOne(mappedBy = "board")
	private BoardDetail boardDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BoardDetail getBoardDetail() {
		return boardDetail;
	}

	public void setBoardDetail(BoardDetail boardDetail) {
		this.boardDetail = boardDetail;
	}
}
