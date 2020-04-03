package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.logic.commands.GoToCanteenCommand.MESSAGE_SUCCESS;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.canteen.Canteen;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit
 * tests for {@code DeleteCommand}.
 */
public class GoToCanteenCommandTest {

    private String nearestBlockName = "COM1";
    private String canteenName = "The Deck";
    private Model model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
    private List<Canteen> canteens = model.getFilteredCanteenList();

    private ModelManager expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Canteen canteen =
                model.getFilteredCanteenList().get(INDEX_FIRST_ITEM.getZeroBased());

        assertCommandSuccess(new GoToCanteenCommand(Index.fromOneBased(1), nearestBlockName),
                GoToCanteenCommand.COMMAND_WORD,
                model, MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_indexIsPresent() {
        /* Optional<Canteen> result = canteens.stream()
                .filter(c -> c.getBlockName().equalsIgnoreCase(nearestBlockName)
                        && c.getName().equals(canteenName))
                .findFirst();

         */

        Index index = Index.fromOneBased(1);
        String expectedMessage = "";
        assertCommandSuccess(new GoToCanteenCommand(index, nearestBlockName), GoToCanteenCommand.COMMAND_WORD,
                model, expectedMessage,
                model);
    }

    @Test
    public void execute_canteenNameIsPresent() {
        String expectedMessage = "";
        assertCommandSuccess(new GoToCanteenCommand(canteenName, nearestBlockName), GoToCanteenCommand.COMMAND_WORD,
                model, expectedMessage,
                model);
    }

    @Test
    public void execute_noValuesPresent() {
        assertCommandFailure(new GoToCanteenCommand("", ""), model,
                Messages.MESSAGE_NOTAVAILABLE);
    }

    @Test
    public void is_equal_command() {
        GoToCanteenCommand command = new GoToCanteenCommand("", "");
        GoToCanteenCommand copy = new GoToCanteenCommand("", "");
        assertTrue(command.equals(command));
        assertTrue(command.equals(copy));
        assertFalse(command.equals(new FavoritesCommand("view")));
    }
}
