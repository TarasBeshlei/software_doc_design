package ua.besh.view.implementation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.besh.dataAccess.domain.Library;
import ua.besh.dataAccess.domain.Role;
import ua.besh.dataAccess.domain.User;
import ua.besh.dataAccess.interfaces.repos.ILibraryRepo;
import ua.besh.dataAccess.interfaces.repos.IUserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private ILibraryRepo libraryRepo;

    @GetMapping("/registration")
    public String registration() {
        return  "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        Library library = new Library();
        libraryRepo.save(library);
        user.setLibrary(library);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
