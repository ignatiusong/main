package seedu.foodiebot.logic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.foodiebot.logic.commands.CommandResult;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.JsonUserPrefsStorage;
import seedu.foodiebot.storage.StorageManager;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;
    private Logic logicCopy;

    @BeforeEach
    public void setUp() {
        JsonFoodieBotStorage addressBookStorage =
            new JsonFoodieBotStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_no_errors() {
        assertDoesNotThrow(() -> logic.setGuiSettings(logic.getGuiSettings()));
        assertDoesNotThrow(() -> logic.getFilteredCanteenListSortedByDistance());
        assertDoesNotThrow(() -> logic.getFilteredStallList(true));
        assertDoesNotThrow(() -> logic.getFilteredFoodList(true));
        assertDoesNotThrow(() -> logic.getFilteredFavoriteFoodList(true));
        assertDoesNotThrow(() -> logic.getFilteredTransactionsList());
        assertDoesNotThrow(() -> logic.getFilteredRandomizeList());
        assertDoesNotThrow(() -> logic.getReport());
        assertDoesNotThrow(() -> logic.getFoodieBot());
    }


    /*
    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }*/

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() throws
        ParseException, IOException, CommandException {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        /* JsonFoodieBotStorage addressBookStorage = new JsonFoodieBotIoExceptionThrowingStub(
                         temporaryFolder.resolve("ioExceptionAddressBook.json"));
         JsonUserPrefsStorage userPrefsStorage =
                 new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
         StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
         logic = new LogicManager(model, storage);

         // Execute add command
         String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_DECK;
         Canteen expectedCanteen = new CanteenBuilder(DECK).withTags().build();
         ModelManager expectedModel = new ModelManager();
         expectedModel.addCanteen(expectedCanteen);
         String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
         assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);*/
        JsonFoodieBotStorage addressBookStorage =
            new JsonFoodieBotIoExceptionThrowingStub(
                temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        logicCopy = new LogicManager(model, storage);
        assertThrows(CommandException.class, () -> logicCopy.execute("favorites set 2142"));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> logic.getFilteredCanteenList().remove(0));
    }

    /**
     * Executes the command and confirms that - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(
        String inputCommand, String expectedMessage, Model expectedModel)
        throws CommandException, ParseException, IOException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is
     * correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message
     * is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is
     * correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(
        String inputCommand,
        Class<? extends Throwable> expectedException,
        String expectedMessage) {
        Model expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(
        String inputCommand,
        Class<? extends Throwable> expectedException,
        String expectedMessage,
        Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonFoodieBotIoExceptionThrowingStub extends JsonFoodieBotStorage {
        private JsonFoodieBotIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFoodieBot(ReadOnlyFoodieBot addressBook, Path filePath, String modelType)
            throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
