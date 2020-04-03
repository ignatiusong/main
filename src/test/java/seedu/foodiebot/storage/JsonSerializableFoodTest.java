package seedu.foodiebot.storage;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;

class JsonSerializableFoodTest {
    @Test
    public void constructor_validFood() {
        new JsonSerializableFood(createSampleModel().getFoodieBot());
    }


    public ModelManager createSampleModel() {
        return new ModelManager(SampleDataUtil.getSampleFoodieBot(), new UserPrefs());
    }
}
