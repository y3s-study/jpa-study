package jpabook.chapter7;

import jpabook.JpaTest;
import jpabook.chapter7.join.Album;
import jpabook.chapter7.join.Item;
import org.junit.jupiter.api.Test;

public class JoinTest extends JpaTest {
    @Test
    void saveTest() {
        Album album = new Album();
        album.setArtist("jun");
        album.setName("akakk");
        album.setPrice(100);

        em.persist(album);

        em.flush();
        em.clear();

        Item item = em.find(Item.class, album.getId()); // Album type
        System.out.println(item);
    }
}
