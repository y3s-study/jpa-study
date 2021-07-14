package jpabook.id;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@TableGenerator(
        name = "BOARDC_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "BOARDC_SEQ", allocationSize = 1
)
public class BoardC {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARDC_SEQ_GENERATOR")
    private Long id;
}
