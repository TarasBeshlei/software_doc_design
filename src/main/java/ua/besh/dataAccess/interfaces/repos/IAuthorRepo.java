package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Author;

import java.util.List;

public interface IAuthorRepo extends JpaRepository<Author, Long> {
    Author getAuthorById(Long id);
    Author getAuthorByName(String name);
    List<Author> findAll();
}
