package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByAuthor_Section_Id(Long idSection);
    List<Review> findAllByAuthor_Section_IdAndAuthor_Member_Id(Long idSection, Long idMember);
}
