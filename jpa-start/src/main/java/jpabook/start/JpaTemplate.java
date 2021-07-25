package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;

public class JpaTemplate {
    private final EntityManagerFactory emf;

    public JpaTemplate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void run(Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
    }
}
