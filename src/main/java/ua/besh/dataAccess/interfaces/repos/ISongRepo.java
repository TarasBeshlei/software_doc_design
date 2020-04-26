package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Song;

import java.util.List;

public interface ISongRepo extends JpaRepository<Song, Long> {
    List<Song> findAll();
    Song getSongById(Long id);
    List<Song> getAllByLibraryId(Long id);
    List<Song> getAllByGenre(String genre);
}
