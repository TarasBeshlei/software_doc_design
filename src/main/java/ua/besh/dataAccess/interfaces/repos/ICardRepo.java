package ua.besh.dataAccess.interfaces.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.besh.dataAccess.domain.Card;

public interface ICardRepo extends JpaRepository<Card, Long> {
    Card getCardById(Long id);
}
