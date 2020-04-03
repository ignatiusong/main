package seedu.foodiebot.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.foodiebot.storage.JsonAdaptedPurchasedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;

class JsonAdaptedPurchasedFoodTest {
    private static final String INVALID_NUMBER_HAS_LETTERS = "five";

    @Test
    public void constructor_validBudget() {
        assertDoesNotThrow(() -> new JsonAdaptedPurchasedFood(new PurchasedFood(SampleDataUtil.getSampleFoods()[0],
            LocalDate.now(), LocalTime.now(), new Rating(5), new Review())));
    }

    @Test
    public void toModelType_invalidValues_throwsIllegalValueException() {
        assertThrows(NumberFormatException.class, () -> new JsonAdaptedPurchasedFood(
            "Combo Set", INVALID_NUMBER_HAS_LETTERS, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            "1", "Nus Flavors", "Western", "2020-03-31",
            "20:02:20", "5", "review"));
        assertThrows(NumberFormatException.class, () -> new JsonAdaptedPurchasedFood(
            "Combo Set", INVALID_NUMBER_HAS_LETTERS, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            INVALID_NUMBER_HAS_LETTERS, "Nus Flavors", "Western", "2020-03-31",
            "20:02:20", "5", "review"));
    }

    @Test

    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPurchasedFood food =
            new JsonAdaptedPurchasedFood(null, "5", "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                "1", "Nus Flavors", "Western", "2020-03-31",
                "20:02:20", "5", "review");
        String expectedMessage =
            String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }
}
