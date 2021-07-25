package jpabook.id;

import jpabook.start.JpaTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class IdMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        JpaTemplate jpaTemplate = new JpaTemplate(emf);

        jpaTemplate.run(em -> {
            BoardB board = new BoardB();
            em.persist(board);
            System.out.println("board.id = " + board.getId());
        });

        jpaTemplate.run(em -> {
            BoardC board = new BoardC();
            em.persist(board);
            System.out.println("board.id = " + board.getId());
        });

        emf.close();
    }
}
