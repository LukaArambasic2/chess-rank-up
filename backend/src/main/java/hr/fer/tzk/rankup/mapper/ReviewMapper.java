package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.form.ReviewDto;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.Review;

public class ReviewMapper {
    public static ReviewDto toDto(Review review) {
        if (review == null) {
            return null;
        }

        ReviewDto dto = new ReviewDto();
        Member author = review.getAuthor().getMember();
        String credentials = author.getFirstName() + " " + author.getLastName();
        dto.setCredentials(credentials);
        dto.setText(review.getText());

        return dto;
    }
}
