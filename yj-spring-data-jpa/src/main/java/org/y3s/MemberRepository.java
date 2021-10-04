package org.y3s;

import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member>, MemberRepositoryCustom {
    @Modifying
    @Query("update Member m set m.name = 'sony'")
    int bulkUpdateName();

    @Lock(value = LockModeType.OPTIMISTIC)
    List<Member> findByName(String name);

    @QueryHints(value = {@QueryHint(name = "org.hibernate.readOnly", value = "true")}, forCounting = true)
    Member findOneByName(String name);
}
