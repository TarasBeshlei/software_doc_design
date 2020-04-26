package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Library;
import ua.besh.dataAccess.domain.User;

public interface IUserRepo extends JpaRepository<User, Long> {
    User findUserById(Long id);
    Long getLibraryById(Long id);
    User findByUsername(String username);
}
