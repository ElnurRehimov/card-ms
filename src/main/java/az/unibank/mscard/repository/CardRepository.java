package az.unibank.mscard.repository;


import az.unibank.mscard.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByPan(String pan);

    List<Card> findAllByUserId(Long userId);

    Optional<Card> findByIdAndUserId(Long id, Long userId);
}