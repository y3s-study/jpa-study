package jpabook.chapter8;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Team8")
@Data
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
