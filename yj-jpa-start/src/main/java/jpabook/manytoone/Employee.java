package jpabook.manytoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
