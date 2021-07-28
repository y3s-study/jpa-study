package jpabook.chapter6.manytomany;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class MemberProductId implements Serializable {
    private Long member; // MemberProduct.member 와 연결
    private Long product; // MemberProduct.product 와 연결
}
