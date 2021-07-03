package jpabook.start;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class JpaTemplate {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

    public static void run(Consumer<EntityManager> consumer) {
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

    public static void close() {
        emf.close();
    }
}
