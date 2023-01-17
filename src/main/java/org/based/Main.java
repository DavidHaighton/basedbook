package org.based;

public class Main {
    public static void main(String[] args) {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Dragon","1111111111"));
        book.addBuddy(new BuddyInfo("SeaOfThieves","2222222222"));
        book.addBuddy(new BuddyInfo("Ligma Johnson","4444444444"));

        System.out.println(book.toString());
    }
}