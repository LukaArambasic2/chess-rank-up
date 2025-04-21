package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByDateCreatedAsc();
    List<News> findAllByAuthor_IdOrderByDateCreated(Long id);

    List<News> findAllBySection_IdOrderByDateCreatedDesc(Long id);
}
