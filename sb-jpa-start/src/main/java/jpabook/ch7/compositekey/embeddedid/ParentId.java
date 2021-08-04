package jpabook.ch7.compositekey.embeddedid;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParentId implements Serializable {
	@Column(name = "PARENT_ID1")
	private String id1;

	@Column(name = "PARENT_ID2")
	private String id2;

	public ParentId() {
	}

	public ParentId(String id1, String id2) {
		this.id1 = id1;
		this.id2 = id2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParentId parentId = (ParentId)o;
		return Objects.equals(id1, parentId.id1) && Objects.equals(id2, parentId.id2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id1, id2);
	}
}
