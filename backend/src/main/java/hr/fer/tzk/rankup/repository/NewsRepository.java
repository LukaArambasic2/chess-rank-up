package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByDateCreatedAsc();
    List<News> findAllByAuthor_IdOrderByDateCreated(Long id);

    List<News> findAllBySection_IdOrderByDateCreated(Long id);
}
