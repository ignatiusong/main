package seedu.foodiebot.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.util.SampleDataUtil;

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

    @Test
    public void are_food_equal() {
        Food food = SampleDataUtil.getSampleFoods()[0];
        Food copy = SampleDataUtil.getSampleFoods()[0];

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(food.hashCode(), copy.hashCode());

        assertFalse(food.equals(canteen));

        assertSame(food, food);
    }

}
