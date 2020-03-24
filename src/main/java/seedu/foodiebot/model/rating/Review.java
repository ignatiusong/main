package seedu.foodiebot.model.rating;

import java.util.Optional;

/** Stores the review for a food */
public class Review {
    public static final String NO_REVIEW = "No review";

    private final Optional<String> review;

    public Review(String review) {
        this.review = Optional.of(review);
    }

    public Review() {
        this.review = Optional.empty();
    }

    public Optional<String> getReview() {
        return this.review;
    }

    @Override
    public String toString() {
        return review.isPresent() ? getReview().get() : NO_REVIEW;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && this.review.get().equals(((Review) other).getReview()));
    }

    @Override
    public int hashCode() {
        return review.hashCode();
    }

}
