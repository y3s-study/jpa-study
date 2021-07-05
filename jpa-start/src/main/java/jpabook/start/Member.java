package jpabook.start;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "MEMBER")
@Setter
@Getter
@DynamicUpdate
@Slf4j
public class Member {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;

    @PrePersist
    public void prePersist() {
        // DB에 저장되기 전 호출됨
        log.info("### prePersist - " + this);
    }

    @PostPersist
    public void postPersist() {
        // DB에 저장된 후 호출됨
        log.info("### postPersist - " + this);
    }

    @PostLoad
    public void postLoad() {
        // DB에서 조회 후 호출됨. 영속성 컨텍스트에서 바로 조회되는 경우 호출되지 않음
        log.info("### postLoad - " + this);
    }

    @PreUpdate
    public void preUpdate() {
        // DB update query 실행 전 호출됨
        log.info("### preUpdate - " + this);
    }

    @PostUpdate
    public void postUpdate() {
        // DB update query 실행 후 호출됨
        log.info("### postUpdate - " + this);
    }

    @PreRemove
    public void preRemove() {
        log.info("### preRemove - " + this);
    }

    @PostRemove
    public void postRemove() {
        log.info("### postRemove - " + this);
    }
}
