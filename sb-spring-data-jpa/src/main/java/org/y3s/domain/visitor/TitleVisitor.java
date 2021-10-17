package org.y3s.domain.visitor;

import org.y3s.domain.Book;

import lombok.Getter;

@Getter
public class TitleVisitor implements Visitor {
	private String title;

	@Override
	public void visit(Book book) {
		title = "[제목:" + book.getName() + " 저자:" + book.getAuthor() + "]";
	}
}
