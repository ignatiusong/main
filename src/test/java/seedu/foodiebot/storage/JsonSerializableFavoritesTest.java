package seedu.foodiebot.storage;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;

class JsonSerializableFavoritesTest {
    @Test
    public void constructor_validFoodieBot() {
        new JsonSerializableFavorites(createSampleModel().getFoodieBot());
    }


    public ModelManager createSampleModel() {
        return new ModelManager(SampleDataUtil.getSampleFoodieBot(), new UserPrefs());
    }
}
