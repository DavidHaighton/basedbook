package org.based;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        /**
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Dragon","1111111111"));
        book.addBuddy(new BuddyInfo("SeaOfThieves","2222222222"));
        book.addBuddy(new BuddyInfo("Ligma Johnson","4444444444"));

        System.out.println(book.toString());

         */
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new BuddyInfo("Jack", "1234567890"));
            repository.save(new BuddyInfo("Chloe", "1111111111"));
            repository.save(new BuddyInfo("Kim", "2222222222"));
            repository.save(new BuddyInfo("David", "3333333333"));
            repository.save(new BuddyInfo("Michelle", "4444444444"));

            // fetch all buddies
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (BuddyInfo customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual buddy by ID
            BuddyInfo customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch buddies by name
            log.info("Customer found with findByName('Jack'):");
            log.info("--------------------------------------------");
            repository.findByName("Jack").forEach(jack -> {
                log.info(jack.toString());
            });
            log.info("");

            // fetch buddies by phone number
            log.info("Customer found with findByPhone('3333333333'):");
            log.info("--------------------------------------------");
            repository.findByPhone("3333333333").forEach(phone -> {
                log.info(phone.toString());
            });
            log.info("");


        };
    }
}