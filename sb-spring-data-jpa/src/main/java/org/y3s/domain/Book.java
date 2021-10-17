package org.y3s.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.y3s.domain.visitor.Visitor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@DiscriminatorValue("B")
@Entity
public class Book extends Item {
	private String name;
	private String author;

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
