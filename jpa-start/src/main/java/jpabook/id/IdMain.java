package jpabook.id;

import jpabook.start.JpaTemplate;

public class IdMain {
    public static void main(String[] args) {
        JpaTemplate.run(em -> {
            BoardB board = new BoardB();
            em.persist(board);
            System.out.println("board.id = " + board.getId());
        });

        JpaTemplate.run(em -> {
            BoardC board = new BoardC();
            em.persist(board);
            System.out.println("board.id = " + board.getId());
        });

        JpaTemplate.close();
    }
}
