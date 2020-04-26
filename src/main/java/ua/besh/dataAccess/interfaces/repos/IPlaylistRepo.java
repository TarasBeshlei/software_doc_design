package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Playlist;

public interface IPlaylistRepo extends JpaRepository<Playlist, Long> {
    Playlist getPlaylistById(Long id);
}
