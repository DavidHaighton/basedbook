package org.based;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * AddressBook class used to create an address book of your buddies
 */
@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;
    @OneToMany(cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    private List<BuddyInfo> buddies;
    /**
     * @param buddies the preset buddies to add
     */
    public AddressBook(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    /**
     * Creates an empty address book
     */
    public AddressBook(){
        this(new ArrayList());
    }

    /**
     * @param buddy the buddy to add to the book
     */
    public void addBuddy(BuddyInfo buddy){
        this.buddies.add(buddy);
    }

    /**
     * @param buddy the buddy to be removed from the address book :(
     */
    public void removeBuddy(BuddyInfo buddy){
        boolean success = buddies.remove(buddy);
        if(!success){
            throw new IllegalArgumentException("Buddy does not exist");
        }
    }

    /**
     * @return the address books contents in string form
     */
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (BuddyInfo info: buddies) {
            output.append(info.toString()).append("\n");
        }
        return output.toString();
    }
}
