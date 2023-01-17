package org.based;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AddressBookTest {

    private AddressBook book;
    @Before
    public void setUp() {
        List<BuddyInfo> a = new ArrayList<>();
        a.add(new BuddyInfo("A", "1234567890"));

        book = new AddressBook(a);
    }

    @Test
    public void addBuddy() {
        BuddyInfo info = new BuddyInfo("B","0123456789");
        book.addBuddy(info);
        assertTrue(book.toString().contains(info.toString()));
    }

    @Test
    public void removeBuddy() {
        BuddyInfo info = new BuddyInfo("A", "1234567890");
        book.removeBuddy(info);
        assertFalse(book.toString().contains(info.toString()));
    }

    @Test
    public void testToString() {
        BuddyInfo info = new BuddyInfo("A", "1234567890");
        assertTrue(book.toString().contains(info.toString()));
    }

    @Test
    public void testConstructor(){
        AddressBook book2 = new AddressBook();
    }

    @Test
    public void testPersistence(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("basedbook");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();


        Query q = em.createQuery("SELECT b FROM AddressBook b");
        @SuppressWarnings("unchecked")
        List<AddressBook> books= q.getResultList();

        assertEquals(1, books.size());
        assertEquals(book,books.get(0));
        em.close();
        emf.close();

    }
}