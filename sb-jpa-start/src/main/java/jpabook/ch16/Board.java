package jpabook.ch16;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Setter;

@Setter
@Entity
public class Board {
	@Id
	private String id;
	private String title;

	@Version
	private Integer version;
}
