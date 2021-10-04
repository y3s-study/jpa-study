package org.y3s;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class MemberSpec {
    public static Specification<Member> memberName(String memberName) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(memberName)) {
                return null;
            }

            return criteriaBuilder.equal(root.get("name"), memberName);
        };
    }
}
