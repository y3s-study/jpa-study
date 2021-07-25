package jpabook.start;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaDirtyCheckingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        JpaTemplate jpaTemplate = new JpaTemplate(emf);

        jpaTemplate.run(em -> {
            Member member = new Member();
            member.setId("100");
            member.setUsername("june");
            member.setAge(10);

            em.persist(member);
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            findMember.setAge(200);

            em.flush();

            em.remove(findMember);
        });

        emf.close();
    }
}
