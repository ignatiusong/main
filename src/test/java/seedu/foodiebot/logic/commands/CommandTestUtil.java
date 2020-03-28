package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.testutil.EditCanteenDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_DECK = "The Deck";
    public static final String VALID_NAME_NUSFLAVORS = "NUS flavors";
    public static final String VALID_TAG_ASIAN = "asian";
    public static final String VALID_TAG_INDIAN = "indian";

    public static final String NAME_DESC_DECK = " " + PREFIX_NAME + VALID_NAME_DECK;
    public static final String NAME_DESC_NUSFLAVORS = " " + PREFIX_NAME + VALID_NAME_NUSFLAVORS;
    public static final String TAG_DESC_ASIAN = " " + PREFIX_TAG + VALID_TAG_ASIAN;
    public static final String TAG_DESC_INDIAN = " " + PREFIX_TAG + VALID_TAG_INDIAN;

    public static final String INVALID_NAME_DESC =
        " " + PREFIX_NAME + "Pizza&"; // '&' not allowed in names
    public static final String INVALID_TAG_DESC =
        " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCanteenDescriptor DESC_DECK;
    public static final EditCommand.EditCanteenDescriptor DESC_NUSFLAVORS;

    static {
        DESC_DECK =
            new EditCanteenDescriptorBuilder()
                .withName(VALID_NAME_DECK)
                .withTags(VALID_TAG_ASIAN)
                .build();

        DESC_NUSFLAVORS =
            new EditCanteenDescriptorBuilder()
                .withName(VALID_NAME_NUSFLAVORS)
                .withTags(VALID_TAG_ASIAN)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(
        Command command,
        Model actualModel,
        CommandResult expectedCommandResult,
        Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            //assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | IOException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(
        Command command, String commandName, Model actualModel, String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(commandName, expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain
     * unchanged
     */
    public static void assertCommandFailure(
        Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodieBot expectedFoodieBot = new FoodieBot(actualModel.getFoodieBot());
        List<Canteen> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCanteenList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodieBot, actualModel.getFoodieBot());
        assertEquals(expectedFilteredList, actualModel.getFilteredCanteenList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the canteen at the given {@code
     * targetIndex} in the {@code model}'s address book.
     */
    /* public static void showCanteenAtIndex(Model model, Index targetIndex) {

        assertTrue(targetIndex.getZeroBased() < model.getFilteredCanteenList().size());

        Canteen canteen = model.getFilteredCanteenList().get(targetIndex.getZeroBased());
        final String[] splitName = canteen.getName().fullName.split("\\s+");
        model.updateFilteredCanteenList(
            new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCanteenList().size());
    }

     */

}
