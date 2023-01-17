package org.based;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddressBookTest {

    private AddressBook book;
    @Before
    public void setUp() {
        book = new AddressBook(new ArrayList<>(List.of(
                new BuddyInfo("A", "1234567890")
        )));
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
}