package com.y3sstudy.skhelloshop.dto.search;

import com.y3sstudy.skhelloshop.domain.Order;
import com.y3sstudy.skhelloshop.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public Specification<Order> toSpecification() {
        return (Specification<Order>) ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (memberName != null) {
                predicates.add(builder.like(root.get("name"), this.memberName + "%"));
            }
            predicates.add(builder.equal(root.get("orderStatus"), this.orderStatus));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}
