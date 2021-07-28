package jpabook.chapter6.manytomany;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private Long id;
    private String username;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts;
}
