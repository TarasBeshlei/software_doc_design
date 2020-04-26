package ua.besh.businessLogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.besh.dataAccess.domain.Author;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.interfaces.repos.IAuthorRepo;

@Component
public class AuthorManager {

    @Autowired
    private IAuthorRepo authorRepo;

    public Iterable<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Iterable<Song> getSongsByAuthor(Long id) {
        return authorRepo.getAuthorById(id).getSongs();
    }

}
