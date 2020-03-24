package seedu.foodiebot.model.rating;

import java.util.Objects;
import java.util.Optional;

/** Stores the rating for a food */
public class Rating {
    public static final String NO_RATING = "No rating";

    private final Optional<Integer> rating;

    public Rating(int rating) throws IllegalArgumentException {
        if (rating >= 0 && rating <= 10) {
            this.rating = Optional.of(rating);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Rating() {
        this.rating = Optional.empty();
    }

    public Optional<Integer> getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return rating.isPresent() ? Integer.toString(this.rating.get()) : NO_RATING;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && this.rating == ((Rating) other).getRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }
}
