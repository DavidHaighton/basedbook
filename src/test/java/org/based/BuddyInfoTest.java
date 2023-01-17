package org.based;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;

import static org.junit.Assert.*;

public class BuddyInfoTest {
    BuddyInfo info;

    @Before
    public void setUp(){
            info = new BuddyInfo("Spiderman", "6136136136");
    }

    @Test
    public void getName() {
        assertEquals("Spiderman", info.getName());
    }

    @Test
    public void getPhone() {
        assertEquals("6136136136", info.getPhone());
    }

    @Test
    public void testToString() {
        assertEquals("Name: Spiderman Phone Number: 6136136136", info.toString());
    }

    @Test
    public void testEquals() {
        BuddyInfo info2 = new BuddyInfo("Spiderman", "6136136136");
        assertEquals(info, info2);
    }

/*    @Test
    public void testConstructor(){
        assertThrows(IllegalArgumentException.class, ()-> new BuddyInfo("", "a"));
        new BuddyInfo("Spiderman", "6136136136");
        new BuddyInfo("", "(613)613-1234");
    }*/

    @Test
    public void testPersistence(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("basedbook");
        EntityManager em = emf.createEntityManager();

        BuddyInfo b = new BuddyInfo("Michael Vezina", "1234567890");
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();


        Query q = em.createQuery("SELECT b FROM BuddyInfo b");
        @SuppressWarnings("unchecked")
        List<BuddyInfo> infos= q.getResultList();

        assertEquals(1, infos.size());
        assertEquals(b,infos.get(0));
        em.close();
        emf.close();

    }
}