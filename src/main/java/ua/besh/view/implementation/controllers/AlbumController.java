package ua.besh.view.implementation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.besh.businessLogic.implementation.AlbumManager;

@Controller
public class AlbumController {

    @Autowired
    private AlbumManager albumManager;

    @GetMapping("/albums")
    public String getAllAlbums(Model model) {
        model.addAttribute("albums", albumManager.getAllAlbums());
        return "albums";
    }

    @RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
    public String getAlbumsSongs(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("songs", albumManager.getAlbumSongs(id));
        return "albumDetail";
    }
}
