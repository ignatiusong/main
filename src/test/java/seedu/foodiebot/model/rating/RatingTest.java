package seedu.foodiebot.model.rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.rating.Rating.NO_RATING;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.util.SampleDataUtil;

class RatingTest {
    @Test
    public void are_rating_equal() {
        Rating rating = new Rating(5);
        Rating copy = new Rating(5);

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(rating.hashCode(), copy.hashCode());

        assertNotEquals(canteen, rating);

        assertTrue(rating.equals(rating));
    }

    @Test
    public void is_valid_rating() {
        Rating rating = new Rating(5);
        assertNotEquals(rating.getRating(), Optional.empty());
        assertEquals(rating.toString(), rating.getRating().get().toString());

        Rating invalidRating = new Rating();
        assertEquals(invalidRating.toString(), NO_RATING);
    }
}
