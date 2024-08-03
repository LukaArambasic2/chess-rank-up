package hr.fer.tzk.rankup.controller;

import hr.fer.tzk.rankup.form.ReviewDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections/{idSection}")
public class ReviewController {

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable Long idSection) {
        return null;
    }

    @PostMapping("/members/{idMember}/review")
    public ResponseEntity<Void> createReviewAsUser(@PathVariable Long idSection, @PathVariable Long idMember) {
        return null;
    }
}
