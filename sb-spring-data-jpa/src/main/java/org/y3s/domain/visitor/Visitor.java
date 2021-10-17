package org.y3s.domain.visitor;

import org.y3s.domain.Book;

public interface Visitor {
	void visit(Book book);
	// void visit(Album album);
	// void visit(Movie movie);
}
