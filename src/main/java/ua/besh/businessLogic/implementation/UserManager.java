package ua.besh.businessLogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.besh.dataAccess.domain.Library;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.domain.User;
import ua.besh.dataAccess.interfaces.repos.IUserRepo;

@Component
public class UserManager {

    @Autowired
    private IUserRepo userRepo;

    public Iterable<Song> getLibrarySong(Authentication authentication) {

        User user = userRepo.findByUsername(authentication.getName());
        Library library = user.getLibrary();

        return library.getSongs();
    }
}
