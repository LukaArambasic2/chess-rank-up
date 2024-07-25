package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Event;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository {

    Optional<Event> findByName(String name);
}
