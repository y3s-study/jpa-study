package jpabook.manytoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Department {
    @Id
    private Long id;
    private String name;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
