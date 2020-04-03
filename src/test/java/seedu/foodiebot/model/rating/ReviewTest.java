package seedu.foodiebot.model.rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.util.SampleDataUtil;

class ReviewTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Review(null));
    }

    @Test
    public void are_stalls_equal() {
        Review review = new Review("review");
        Review copy = new Review("review");

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(review.hashCode(), copy.hashCode());

        assertNotEquals(canteen, review);

        assertSame(review, review);
    }
}
