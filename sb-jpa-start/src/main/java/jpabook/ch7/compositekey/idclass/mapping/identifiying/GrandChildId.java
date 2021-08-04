package jpabook.ch7.compositekey.idclass.mapping.identifiying;

import java.io.Serializable;
import java.util.Objects;

public class GrandChildId implements Serializable {
	private ChildId child;	//GrandChild.child 매핑
	private String id;		//GrandChild.id 매핑

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		GrandChildId that = (GrandChildId)o;
		return Objects.equals(child, that.child) && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(child, id);
	}
}
