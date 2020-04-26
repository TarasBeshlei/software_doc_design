package ua.besh.view.implementation.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.besh.businessLogic.implementation.UserManager;
import ua.besh.dataAccess.domain.Album;
import ua.besh.dataAccess.domain.Author;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.interfaces.repos.IAlbumRepo;
import ua.besh.dataAccess.interfaces.repos.IAuthorRepo;
import ua.besh.dataAccess.interfaces.repos.ISongRepo;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private ISongRepo songRepo;

    @Autowired
    private IAlbumRepo albumRepo;

    @Autowired
    private IAuthorRepo authorRepo;

    @GetMapping("/user")
    public String userHome(Authentication authentication, Model model) {
        Iterable<Song> songs = userManager.getLibrarySong(authentication);

        model.addAttribute("songs", songs);

        return "user";
    }

    @GetMapping("/fill")
    public void fillNullsInSongs() {
        Album album = albumRepo.getById(205L);
        Author author = authorRepo.getAuthorById(4L);
        Iterable<Song> songs = songRepo.findAll();
        songs.forEach(song -> song.setAlbum(album));
        songs.forEach(song -> song.setAuthor(author));
        songRepo.saveAll(songs);
    }

    @GetMapping("/fillRelation")
    public void fillNullsInrelation() {
        List<Song> songs = songRepo.findAll();
        List<Album> albums = albumRepo.findAll();
        List<Author> authors = authorRepo.findAll();
//        albums.forEach(album -> album.getSongs().add(song));
//        for (Album album: albums) {
//
//        }
        Album album = albumRepo.getById(205L);
        Author author = authorRepo.getAuthorById(4L);
//        songs.forEach(song -> album.getSongs().add(song));
        songs.forEach(song -> author.getSongs().add(song));
//        authors.forEach(author -> author.getSongs().add(song));
//        authorRepo.saveAll(authors);
//        albumRepo.saveAll(albums);
        authorRepo.save(author);
//        albumRepo.save(album);
    }
}
