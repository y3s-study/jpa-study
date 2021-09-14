package jpabook.ch10.jpql;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import jpabook.ch10.querydsl.OrderItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SqlResultSetMapping(name = "OrderResults",
	entities = {
		@EntityResult(entityClass = Order.class, fields = {
			@FieldResult(name = "id", column = "order_id"),
			@FieldResult(name = "quantity", column = "order_quantity"),
			@FieldResult(name = "item", column = "order_item")})},
	columns = {
		@ColumnResult(name = "item_name")}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Order {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;
}
