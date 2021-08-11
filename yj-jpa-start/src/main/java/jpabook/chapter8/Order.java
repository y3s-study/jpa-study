package jpabook.chapter8;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "member")
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
