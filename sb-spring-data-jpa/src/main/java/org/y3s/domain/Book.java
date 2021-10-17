package org.y3s.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@DiscriminatorValue("B")
@Entity
public class Book extends Item {
	private String name;
	private String author;

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

	@Override
	public String getTitle() {
		return "[제목:" + getName() + " 저자:" + author + "]";
	}
}
