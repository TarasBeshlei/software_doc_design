package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Album;

public interface IAlbumRepo extends JpaRepository<Album, Long> {
    Album getById(Long id);
    Album getAlbumByName(String name);
}
