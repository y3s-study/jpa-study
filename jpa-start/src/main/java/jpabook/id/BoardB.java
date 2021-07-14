package jpabook.id;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class BoardB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    private Long id;
}
