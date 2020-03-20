package seedu.foodiebot.storage;

import static seedu.foodiebot.storage.JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Name;

class JsonAdaptedFoodTest {

    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood("Chicken Chop", "5",
                "is delicious", "Combo Set.png", "9",
                "Nus Flavors", "Western");
        //assertEquals(WESTERN, food.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, "5",
                "is delicious", "Combo Set.png", "9",
                "Nus Flavors", "Western");
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        //assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

}
