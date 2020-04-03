package seedu.foodiebot.model.favorites;

import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FavoriteFoodTest {
    @Test
    public void constructor_invalidParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FavoriteFood(null, 0,
            "Food", "Cai Fan.png", 1, "The Deck",
            "Cai Fan", getTagSet("expensive")));
    }
}
