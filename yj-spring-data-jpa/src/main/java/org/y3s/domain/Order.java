package org.y3s.domain;

import lombok.Getter;
import org.y3s.Member;

import javax.persistence.*;

@Entity
@Getter
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;
}
