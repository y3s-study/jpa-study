package jpabook.start;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        JpaTemplate jpaTemplate = new JpaTemplate(emf);

        jpaTemplate.run(em -> {
            Member member = new Member("id1", "멤버", 2, RoleType.ADMIN, new Date());
            em.persist(member);
            System.out.println("member : " + member);

            member.setAge(200);

            Member findMember = em.find(Member.class, "id1");
            System.out.println("findMember : " + findMember);

            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();
            System.out.println("members.size = " + members.size());

//            em.remove(member);
        });

        emf.close();
    }
}
