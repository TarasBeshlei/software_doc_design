package ua.besh.businessLogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.interfaces.repos.ISongRepo;

@Component
public class SongManager {

    @Autowired
    private ISongRepo songRepo;

    public Iterable<Song> getSongList() {
        return songRepo.findAll();
    }

    public Iterable<Song> getSongByGenre(String genre) {
        return songRepo.getAllByGenre(genre);
    }
}
