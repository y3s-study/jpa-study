package jpabook.start;

public class JpaDirtyCheckingMain {
    public static void main(String[] args) {
        JpaTemplate.run(em -> {
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
        JpaTemplate.close();
    }
}
