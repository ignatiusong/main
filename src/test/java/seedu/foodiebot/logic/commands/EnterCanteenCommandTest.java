package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

class EnterCanteenCommandTest {
    private final String invalidCanteenName = "My House";
    private final String validCanteenName = "The Deck";

    private Model model;
    private Model expectedModel;

    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_success() {

        /*assertCommandSuccess(new EnterCanteenCommand(Index.fromOneBased(1)),
                EnterCanteenCommand.COMMAND_WORD, model,
                EnterCanteenCommand.MESSAGE_SUCCESS, expectedModel);
        */

        /*assertCommandSuccess(new EnterCanteenCommand(validCanteenName),
                EnterCanteenCommand.COMMAND_WORD, model,
                EnterCanteenCommand.MESSAGE_SUCCESS, expectedModel);
                */

    }
}
