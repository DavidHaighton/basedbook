package org.based;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BuddyInfoTest {
    BuddyInfo info;
    @Autowired
    private BuddyInfoRepository repo;

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

    @Test
    public void testPersistence(){

        repo.deleteAll();
        assertEquals(0, repo.count());
        repo.save(info);
        assertEquals(info, repo.findById(info.getId()));
        assertEquals(1, repo.count());

        repo.deleteAll();

    }

}