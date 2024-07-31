package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Event;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByName(String name);

    List<Event> findByIdSection(Long idSection);

    List<Event> findByDateBetween(java.time.LocalDate startDate, java.time.LocalDate endDate);
}
