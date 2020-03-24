package seedu.foodiebot.ui;

import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

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

@ExtendWith(ApplicationExtension.class)
class MainWindowTest {

    private FoodieBotStorage foodieBotStorage =
        new JsonFoodieBotStorage(Paths.get("foodiebot.json"),
            Paths.get("foodiebot-stalls"), Paths.get("foodiebot-budget"),
            Paths.get("foodiebot-food"), Paths.get("foodiebot-favorites"),
            Paths.get("foodiebot-transactions"));
    private UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Paths.get("preferences.json"));
    private Storage storage = new StorageManager(foodieBotStorage, userPrefsStorage);
    private MainWindow mainWindow;

    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Start
    public void init(Stage stage) {
        mainWindow = new MainWindow(stage, new LogicManager(createSampleModel(), storage));
        mainWindow.show();
    }

    @Test
    public void close_menuBarExitButton_allWindowsClosed(FxRobot robot) {
        //robot.clickOn("#exit");
        //robot.clickOn("#file");
        // The application will exit when all windows are closed.
        //assertEquals(Collections.emptyList(), robot.listWindows());
    }

    public ModelManager createSampleModel() {
        return new ModelManager(SampleDataUtil.getSampleFoodieBot(), new UserPrefs());
    }
}
