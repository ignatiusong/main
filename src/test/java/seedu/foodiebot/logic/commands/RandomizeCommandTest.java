package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.randomize.Randomize;

class RandomizeCommandTest {

    private Model model;
    private Model expectedModel;
    private Randomize randomize = Randomize.checkRandomize();


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_viewFavorite_success() {
        //assertCommandSuccess(new RandomizeCommand("", "all", randomize), RandomizeCommand.COMMAND_WORD, model,
        //    RandomizeCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
