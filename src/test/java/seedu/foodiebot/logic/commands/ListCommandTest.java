package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }


    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ListCommand command = new ListCommand();
        assertCommandSuccess(command, ListCommand.COMMAND_WORD, model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        assertCommandSuccess(new ListCommand(), ListCommand.COMMAND_WORD, model,
            ListCommand.MESSAGE_SUCCESS, expectedModel);
    }


    @Test
    public void execute_nearestBlockIsNotBlank() {
        ListCommand command = new ListCommand("COM1");
        assertCommandSuccess(command, ListCommand.COMMAND_WORD, model,
                ListCommand.MESSAGE_NEAREST_BLOCK, expectedModel);
    }
}
