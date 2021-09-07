package jpabook.ch10.querydsl.delegatemethod;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;

import jpabook.ch10.querydsl.Item;
import jpabook.ch10.querydsl.QItem;

public class ItemExpression {
	@QueryDelegate(Item.class)
	public static BooleanExpression isExpensive(QItem item, Integer price) {
		return item.price.gt(price);
	}
}
