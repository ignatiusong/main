package seedu.foodiebot.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.util.SampleDataUtil;

class PurchasedFoodTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new PurchasedFood(null, 5, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                1, "Nus Flavors", "Western", getTagSet("asian"), LocalDate.of(2020, 03, 12),
                LocalTime.of(20, 20), new Rating(5), new Review("review")));
    }

    @Test
    public void are_food_equal() {
        PurchasedFood food = new PurchasedFood("ComboSet", 5, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            1, "Nus Flavors", "Western", getTagSet("asian"), LocalDate.of(2020, 3, 12),
            LocalTime.of(20, 20), new Rating(5), new Review("review"));
        PurchasedFood copy = new PurchasedFood("ComboSet", 5, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            1, "Nus Flavors", "Western", getTagSet("asian"), LocalDate.of(2020, 3, 12),
            LocalTime.of(20, 20), new Rating(5), new Review("review"));

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(food.hashCode(), copy.hashCode());

        assertEquals(food.compareTo(copy), 0);
        assertEquals(food.hashCode(), copy.hashCode());

        copy.setReview(new Review("another review"));
        assertNotEquals(food, copy);

        assertNotEquals(canteen, food);

        assertTrue(food.isSameFood(food));
    }
}
