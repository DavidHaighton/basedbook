package org.based;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class AddressBookController {

    private AddressBookRepository addyRepo;
    private BuddyInfoRepository buddyInfoRepository;

    @Autowired
     public AddressBookController(AddressBookRepository repo, BuddyInfoRepository birepo){
        addyRepo = repo;
        buddyInfoRepository = birepo;
     }

    // create address book
    @PostMapping(path="books")
    public AddressBook postBook(){
        var book = new AddressBook();
        return addyRepo.save(book);
    }
    // add buddy
    @PostMapping(path="books/{id}")
    public AddressBook addBuddy(@PathVariable("id") long bookId, @RequestBody BuddyInfo buddy){

        var book = addyRepo.findById(bookId);
        buddyInfoRepository.save(buddy);
        book.addBuddy(buddy);
        return addyRepo.save(book);
    }
    // remove buddy
    @DeleteMapping(path="books/{bookId}/{buddyId}")
    public AddressBook removeBuddy(@PathVariable("bookId") long bookId, @PathVariable("buddyId") long buddyId){
        var book = addyRepo.findById(bookId);
        book.removeBuddy(buddyId);
        return addyRepo.save(book);
    }



}
