package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;

import seedu.foodiebot.logic.LogicManager;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.JsonUserPrefsStorage;
import seedu.foodiebot.storage.StorageManager;

class UiManagerTest {
    @TempDir
    public Path temporaryFolder;
    private LogicManager logic;
    private UiManager manager = null;
    private Model model = new ModelManager();

    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @BeforeEach
    public void setUp() {
        JsonFoodieBotStorage addressBookStorage =
            new JsonFoodieBotStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Start
    void init(Stage stage) {
        assertDoesNotThrow(() -> new UiManager(logic).start(stage));
    }

    @Test
    void showAlertDialogAndWait() {
        assertThrows(Exception.class, () -> new UiManager(logic).start(null));
        //Platform.runLater(() ->
        //    assertDoesNotThrow(() -> new UiManager(logic).showAlertDialogAndWait(Alert.AlertType.ERROR,
        //        "Error", "Error", "Text")));
    }
}
