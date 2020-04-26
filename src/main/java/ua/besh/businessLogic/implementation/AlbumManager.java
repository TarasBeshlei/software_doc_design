package ua.besh.businessLogic.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.besh.dataAccess.domain.Album;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.interfaces.repos.IAlbumRepo;

@Component
public class AlbumManager {

    @Autowired
    private IAlbumRepo albumRepo;

    public Iterable<Album> getAllAlbums() {
        return albumRepo.findAll();
    }

    public Iterable<Song> getAlbumSongs(Long id) {
        return albumRepo.getById(id).getSongs();
    }
}
