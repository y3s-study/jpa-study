package org.y3s;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
@ToString
@NamedQuery(name="Member.findByName", query = "select m from Member m where m.name =: name")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private int age;

    public Member(String name) {
        this.name = name;
    }
}
