package jpabook.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
@ToString(exclude = "order")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
