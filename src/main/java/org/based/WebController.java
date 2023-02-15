package org.based;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Log
@Controller
public class WebController {

    private AddressBookRepository addyRepo;
    private BuddyInfoRepository buddyRepo;

    @Autowired
    public WebController(AddressBookRepository repo, BuddyInfoRepository brepo){
        addyRepo = repo;
        buddyRepo = brepo;
    }
    @GetMapping(path="books/{bookId}")
    public String getBook(@PathVariable("bookId") long bookId, Model model){
        var book = addyRepo.findById(bookId);
        if(book == null){
            throw new ResourceNotFoundException();
        }
        model.addAttribute("contacts",book);
        model.addAttribute("buddy", new BuddyInfo());
        return "book";
    }


    @GetMapping(path="index")
    public String getBook(Model model){
        var books = addyRepo.findAll();
        var ids = new ArrayList<>();
        for(var bruh: books){
            ids.add(bruh.getId());
        }
        model.addAttribute("books",ids);
        return "index";
    }


    @PostMapping("/addy/{id}")
    public String createBuddy(@PathVariable Long id , @ModelAttribute BuddyInfo buddy, Model model){
        var temp = new BuddyInfo();
        temp.setPhone(buddy.getPhone());
        temp.setName(buddy.getName());
        var book = addyRepo.findById(id);
        log.info("id: "+buddy.getId());
        var bruh = buddyRepo.save(temp);
        book.get().addBuddy(bruh);
        addyRepo.save(book.get());
        return "redirect:/books/"+book.get().getId();
    }

    @PostMapping("/addy")
    public String createBuddy(){
        var book = new AddressBook();
        addyRepo.save(book);
        return "redirect:/index";
    }
}
