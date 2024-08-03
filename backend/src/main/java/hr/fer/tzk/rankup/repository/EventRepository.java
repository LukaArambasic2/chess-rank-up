package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByName(String name);

    List<Event> findAllByDateBetweenOrderByDate(LocalDate dateFrom, LocalDate dateTo);
}
