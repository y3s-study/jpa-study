package org.y3s.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.y3s.domain.visitor.Visitor;

import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item {
	@Id
	@GeneratedValue
	private Long id;

	public abstract void accept(Visitor visitor);
}
