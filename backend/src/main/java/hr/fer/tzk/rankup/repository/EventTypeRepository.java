package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    Optional<EventType> findById(Long id);
}
