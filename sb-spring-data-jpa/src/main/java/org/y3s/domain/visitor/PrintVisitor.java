package org.y3s.domain.visitor;

import org.y3s.domain.Book;

public class PrintVisitor implements Visitor {
	@Override
	public void visit(Book book) {
		//넘어오는 book은 Proxy가 아닌 원본 엔티티다.
		System.out.println("book.getClass() = " + book.getClass());
		System.out.println("[PrintVisitor] [제목:" + book.getName() + " 저자:" + book.getAuthor() + "]");
	}
}
