package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

class SelectItemCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_success() {
        assertCommandSuccess(new SelectItemCommand(),
                SelectItemCommand.COMMAND_WORD, model,
                SelectItemCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
