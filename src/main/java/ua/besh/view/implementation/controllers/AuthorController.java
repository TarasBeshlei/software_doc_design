package ua.besh.view.implementation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.besh.businessLogic.implementation.AuthorManager;
import ua.besh.dataAccess.interfaces.repos.IAuthorRepo;

@Controller
public class AuthorController {

    @Autowired
    private AuthorManager authorManager;

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authorManager.getAuthors());
        return "author";
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    public String getAuthorsSongs(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("songs", authorManager.getSongsByAuthor(id));
        return "authorDetails";
    }
}
