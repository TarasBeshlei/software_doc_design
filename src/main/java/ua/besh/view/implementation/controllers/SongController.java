package ua.besh.view.implementation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.besh.businessLogic.implementation.SongManager;
import ua.besh.dataAccess.domain.Library;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.interfaces.repos.ILibraryRepo;
import ua.besh.dataAccess.interfaces.repos.ISongRepo;
import ua.besh.dataAccess.interfaces.repos.IUserRepo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class SongController {

    @Autowired
    private SongManager songManager;

    @Autowired
    private ILibraryRepo libraryRepo;

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private ISongRepo songRepo;

    @GetMapping("/songs")
    public String getSong(Model model, Authentication authentication) {
        List<Song> librarySongs = userRepo.findByUsername(authentication.getName()).getLibrary().getSongs();
        Set<Long> songInLibrary = StreamSupport.stream(librarySongs.spliterator(), false)
                .map(task -> task.getId())
                .collect(Collectors.toSet());
        model.addAttribute("songs", songManager.getSongList());
        model.addAttribute("libraryKey", songInLibrary);
        return "songs";
    }

    @RequestMapping(value = "/genre/{genre}", method = RequestMethod.GET)
    public String getAuthorsSongs(@PathVariable(value = "genre") String genre, Model model) {
        model.addAttribute("songs", songManager.getSongByGenre(genre));
        return "authorDetails";
    }

    @RequestMapping(value = "/song/{id}", method = RequestMethod.POST)
    public String addInLibrary(@PathVariable(value = "id") Long id, Authentication authentication) {
        Library library = userRepo.findByUsername(authentication.getName()).getLibrary();

        if(!library.getSongs().contains(songRepo.getSongById(id))) {
            library.getSongs().add(songRepo.getSongById(id));
            libraryRepo.save(library);
        }

        return "redirect:/songs";
    }

    @RequestMapping(value = "/song/delete/{id}", method = RequestMethod.POST)
    public String deleteFromLibrary(@PathVariable(value = "id") Long id, Authentication authentication) {
        Library library = userRepo.findByUsername(authentication.getName()).getLibrary();
        library.getSongs().remove(songRepo.getSongById(id));
        libraryRepo.save(library);
        return "redirect:/user";
    }
}
