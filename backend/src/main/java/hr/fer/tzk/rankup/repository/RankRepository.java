package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface RankRepository extends JpaRepository<Rank, Long> {
    Optional<Rank> findByNameAndSection_Id(String name, Long sectionId);
}
