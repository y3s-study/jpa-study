package jpabook.chapter6.onetomany;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "TEAM_ID") // MEMBER νμ΄λΈμ FK
    private List<Member> members = new ArrayList<>();
}
