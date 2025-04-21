package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
