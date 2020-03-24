package seedu.foodiebot;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.stage.Stage;

import seedu.foodiebot.logic.LogicManager;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.storage.FoodieBotStorage;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.JsonUserPrefsStorage;
import seedu.foodiebot.storage.Storage;
import seedu.foodiebot.storage.StorageManager;
import seedu.foodiebot.storage.UserPrefsStorage;
import seedu.foodiebot.ui.UiManager;


class MainAppTest {

    private FoodieBotStorage foodieBotStorage =
        new JsonFoodieBotStorage(Paths.get("foodiebot.json"),
            Paths.get("foodiebot-stalls"), Paths.get("foodiebot-budget"),
            Paths.get("foodiebot-food"), Paths.get("foodiebot-favorites"),
            Paths.get("foodiebot-transactions"));
    private UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("preferences.json"));
    private Storage storage = new StorageManager(foodieBotStorage, userPrefsStorage);
    private MainApp app = new MainApp();

    /* @BeforeAll */
    /*public static void setUpClass() throws Exception {
        //ApplicationTest.launch(MainApp.class);
    }
    */

    @Test
    public void init() throws Exception {
        app.init();
        assert MainApp.getLogger() != null;
        app.model = createSampleModel();
        app.storage = storage;
        app.logic = new LogicManager(app.model, app.storage);
        app.ui = new UiManager(app.logic);

        assert app.model != null;
        assert app.storage != null;

    }

    public ModelManager createSampleModel() {
        return new ModelManager(SampleDataUtil.getSampleFoodieBot(), createSamplePrefs());
    }

    public StorageManager createSampleStorage() {
        return new StorageManager(foodieBotStorage, userPrefsStorage);
    }


    public UserPrefs createSamplePrefs() {
        return new UserPrefs();
    }

    public void start(Stage stage) {
        assert app.ui != null;
    }

    /*
    @Test
    public void stop() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    */
}
