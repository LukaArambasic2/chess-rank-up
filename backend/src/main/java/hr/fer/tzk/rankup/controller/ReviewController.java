package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.form.ReviewDto;
import hr.fer.tzk.rankup.mapper.ReviewMapper;
import hr.fer.tzk.rankup.model.Review;
import hr.fer.tzk.rankup.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(" /sections/{idSection}")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable Long idSection) {
        List<Review> reviews = reviewService.findAllReviewsBySectionId(idSection);
        List<ReviewDto> reviewsDto = reviews.stream()
                .map(ReviewMapper::toDto)
                .toList();
        return ResponseEntity.ok(reviewsDto);
    }

    @GetMapping("/members/{idMember}/review")
    public ResponseEntity<List<ReviewDto>> getReviewsByAuthor(@PathVariable Long idSection, @PathVariable Long idMember) {
        List<Review> reviews = reviewService.findAllReviewsBySectionIdAndMemberId(idSection, idMember);
        List<ReviewDto> reviewsDto = reviews.stream()
                .map(ReviewMapper::toDto)
                .toList();
        return ResponseEntity.ok(reviewsDto);
    }

    @PostMapping("/members/{idMember}/review")
    public ResponseEntity<Void> createReviewAsUser(@PathVariable Long idSection, @PathVariable Long idMember) {
        return null;
    }
}
