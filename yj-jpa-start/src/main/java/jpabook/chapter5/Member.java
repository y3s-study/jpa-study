package jpabook.chapter5;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "Ch5Member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "MEMEER_ID")
    private String id;
    private String username;

    // 연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    // 연관관계 설정
    public void setTeam(Team team) {
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }

        this.team = team;
        Optional.ofNullable(team).ifPresent(t -> t.getMembers().add(this));
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
