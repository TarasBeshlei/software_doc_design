package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Library;
import ua.besh.dataAccess.domain.Song;
import ua.besh.dataAccess.domain.User;

import java.util.List;

public interface ILibraryRepo extends JpaRepository<Library, Long> {
    Library getLibrariesById(Long id);
    List<Song> getSongsById(Long id);

}
