package jpabook.chapter8;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Team8")
@Slf4j
public class Team {
    @Id
    @GeneratedValue
    private Long pk;
    @Setter
    @Getter
    private String name;

    public Long getPk() {
        log.info("[test] getId called. {}", this.getClass().getName());
        return this.pk;
    }
}
