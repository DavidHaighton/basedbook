package org.based;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    private AddressBookRepository addyRepo;

    @Autowired
    public WebController(AddressBookRepository repo){
        addyRepo = repo;
    }
    @GetMapping(path="books/{bookId}")
    public String getBook(@PathVariable("bookId") long bookId, Model model){
        var book = addyRepo.findById(bookId);
        model.addAttribute("contacts",book);
        return "book";
    }
}
