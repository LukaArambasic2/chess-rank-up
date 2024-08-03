package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Review;
import hr.fer.tzk.rankup.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> findAllReviewsBySectionId(Long idSection) {
        return reviewRepository.findAllByAuthor_Section_Id(idSection);
    }

    public List<Review> findAllReviewsBySectionIdAndMemberId(Long idSection, Long idMember) {
        return reviewRepository.findAllByAuthor_Section_IdAndAuthor_Member_Id(idSection, idMember);
    }

    public Optional<Review> createReview() {
        return Optional.empty();
    }
}
