package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.budget.Budget;

class SelectItemCommandTest {
    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }


    @Test
    public void execute_selectSuccess() {
        SelectItemCommand command = new SelectItemCommand(INDEX_FIRST_ITEM);
        model.setBudget(new Budget(0f, "d/"));
        //assertCommandFailure(command, model, SelectItemCommand.EXCEEDED_BUDGET);
    }
}
