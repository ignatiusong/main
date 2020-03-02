package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        ClearCommand command = new ClearCommand();
        assertCommandSuccess(
             command , ClearCommand.COMMAND_WORD, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel.setFoodieBot(new FoodieBot());

        ClearCommand command = new ClearCommand();
        assertCommandSuccess(
            command , ClearCommand.COMMAND_WORD, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
