package jpabook.chapter8;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Member8")
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders;

}
