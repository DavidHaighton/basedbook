package org.based;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Information about one of your buddies
 */
@NoArgsConstructor @Entity
public class BuddyInfo implements Serializable {
    private final static String PHONE_REGEX = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    @Getter @Setter
    private String name;
    @Getter
    private String phone;

    @Id @Getter @GeneratedValue
    private Integer id;

    /**
     * @param name the name of your buddy
     * @param phone the phone number of your buddy
     */
    public BuddyInfo(String name, String phone) {
        this.name = name;
        this.setPhone(phone);
    }

    public void setPhone(String phone) {
        final Pattern phoneRegex = Pattern.compile(PHONE_REGEX);
        if(!phoneRegex.matcher(phone).matches()){ throw new IllegalArgumentException("Phone number invalid: "+phone);}
        this.phone = phone;
    }

    /**
     * @return the buddy in an easy way to read string
     */
    public String toString(){
        return "Name: " + this.getName() + " Phone Number: " + this.getPhone();
    }

    /**
     * @param other the other object to compare to
     * @return true if the objects share the same contents
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof BuddyInfo)) {
            return false;
        }
        BuddyInfo info = (BuddyInfo)other;
        return info.getPhone() == this.getPhone() && info.getName().equals(this.getName());

    }
}
