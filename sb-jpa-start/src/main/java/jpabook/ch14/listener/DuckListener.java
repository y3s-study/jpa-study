package jpabook.ch14.listener;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class DuckListener {
	@PrePersist
	// 특정 타입이 확실하면 특정 타입을 받을 수 있다
	private void prePersist(Object obj) {
		System.out.println("DuckListener.prePersist obj = [" + obj + "]");
	}

	@PostPersist
	// 특정 타입이 확실하면 특정 타입을 받을 수 있다
	private void postPersist(Object obj) {
		System.out.println("DuckListener.postPersist obj = [" + obj + "]");
	}
}
