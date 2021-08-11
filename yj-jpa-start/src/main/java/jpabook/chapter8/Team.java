package jpabook.chapter8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Team8")
@Data
@Slf4j
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        log.info("[test] getId called. {}", this.getClass().getName());
        return this.id;
    }
}
