package jpabook.ch14.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private String content;

	@OneToMany(mappedBy = "board")
	@OrderColumn(name = "POSITION")
	private List<Comment> comments = new ArrayList<>();

	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
