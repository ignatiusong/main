package seedu.foodiebot.storage;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;

class JsonSerializableStallTest {

    @Test
    public void toModelType_duplicateStall() {
        JsonAdaptedFood food = new JsonAdaptedFood("Chicken Chop", "5",
            "is delicious", "ComboSet.png", "9",
            "Nus Flavors", "Western");
    }

    @Test
    public void constructor_validStall() {
        new JsonSerializableStall(createSampleModel().getFoodieBot());
    }


    public ModelManager createSampleModel() {
        return new ModelManager(SampleDataUtil.getSampleFoodieBot(), new UserPrefs());
    }
}
