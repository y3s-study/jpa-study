package jpabook.ch14.listener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;

@Entity
@EntityListeners(DuckListener.class)
public class Duck {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	// 엔티티에서 이벤트를 직접 받는 방식
	// @PrePersist
	// public void prePersist() {
	// 	System.out.println("Duck.prePersist id=" + id);
	// }
	//
	// @PostPersist
	// public void postPersist() {
	// 	System.out.println("Duck.postPersist id=" + id);
	// }
	//
	// @PostLoad
	// public void postLoad() {
	// 	System.out.println("Duck.postLoad id=" + id);
	// }
	//
	// @PreRemove
	// public void preRemove() {
	// 	System.out.println("Duck.preRemove");
	// }
	//
	// @PostRemove
	// public void postRemove() {
	// 	System.out.println("Duck.postRemove");
	// }

	public void setName(String name) {
		this.name = name;
	}
}
