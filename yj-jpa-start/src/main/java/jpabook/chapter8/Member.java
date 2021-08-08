package jpabook.chapter8;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Member8")
@Data
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @ManyToOne
    private Team team;

}
