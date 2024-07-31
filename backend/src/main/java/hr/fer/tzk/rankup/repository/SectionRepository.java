package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findByName(String name);

    List<Section> findByDescriptionContaining(String keyword);

    List<Section> findByLogo(String logo);
}
