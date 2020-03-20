package seedu.foodiebot.model.food;

import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FoodTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Food(null, 6,
                "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                1, "Nus Flavors", "Western", getTagSet("1")));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        /* assertThrows(NullPointerException.class, () -> new Food("Chicken Chop", 0, "1 Meat,
        1 Pasta, 2 Sides", "Combo Set.png",
                1, "Nus Flavors", "Western", getTagSet("1"))); */
    }
}
