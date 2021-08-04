package jpabook.ch7.compositekey.embeddedid.mapping.identifying;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GrandChildId implements Serializable {
	private ChildId childId;

	@Column(name = "GRANDCHILD_ID")
	private String id;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GrandChildId that = (GrandChildId)o;
		return Objects.equals(childId, that.childId) && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(childId, id);
	}
}
