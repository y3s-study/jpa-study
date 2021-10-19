package jpabook.chapter14;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Order.withMember",
                attributeNodes = {
                        @NamedAttributeNode("member")
                }),
        @NamedEntityGraph(
                name = "Order.withAll",
                attributeNodes = {
                        @NamedAttributeNode("member"),
                        @NamedAttributeNode(value = "orderItems", subgraph = "orderItems")
                },
                subgraphs = @NamedSubgraph(name = "orderItems", attributeNodes = {
                        @NamedAttributeNode("item")
                }))
})
public class Order {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
}
