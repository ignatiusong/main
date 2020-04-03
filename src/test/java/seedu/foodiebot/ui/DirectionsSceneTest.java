package seedu.foodiebot.ui;

import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import seedu.foodiebot.logic.LogicManager;
import seedu.foodiebot.logic.commands.DirectionsCommandResult;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.JsonUserPrefsStorage;
import seedu.foodiebot.storage.StorageManager;

@ExtendWith(ApplicationExtension.class)
class DirectionsSceneTest {
    @TempDir
    public Path temporaryFolder;
    private LogicManager logic;
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

    /**
     * .
     */
    @Test
    public void constructor_invalidArgs() {
        assertThrows(NullPointerException.class, () ->
            new DirectionsScene(null, logic, new DirectionsCommandResult(SampleDataUtil.getSampleCanteens()[0],
                "displayed")));
    }
}
