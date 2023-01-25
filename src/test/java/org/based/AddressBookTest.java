package org.based;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressBookTest {

    private AddressBook book;
    @Autowired
    private AddressBookRepository repo;
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
        new AddressBook();
    }

    @Test
    public void testPersistence(){
        repo.deleteAll();
        assertEquals(0, repo.count());
        repo.save(book);
        AddressBook book2 = repo.findById(book.getId());
        assertEquals(book, book2);
        assertEquals(1, repo.count());

        assertEquals(new BuddyInfo("A", "1234567890"),book2.getBuddies().get(0));

        repo.deleteAll();

    }
}