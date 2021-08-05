package jpabook.chapter7.join;

import jpabook.start.JpaTemplate;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JoinMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        JpaTemplate jpaTemplate = new JpaTemplate(emf);

        jpaTemplate.run(em -> {
            Album album = new Album();
            album.setArtist("jun");
            album.setName("akakk");
            album.setPrice(100);

            em.persist(album);

            em.flush();
            em.clear();

            Item item = em.find(Item.class, album.getId()); // Album type
            System.out.println(item);
        });


        emf.close();
    }
}
