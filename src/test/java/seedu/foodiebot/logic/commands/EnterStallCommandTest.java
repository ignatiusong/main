package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;

class EnterStallCommandTest {
    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_success() {
        Canteen testCanteen = new Canteen(new Name("The Deck"), 0, 0, "",
                "", "", new HashSet<>(), "", new ArrayList<>());
        ParserContext.setCurrentCanteen(Optional.of(testCanteen));
        assertCommandSuccess(new EnterStallCommand("Western"), EnterStallCommand.COMMAND_WORD, model,
                "", expectedModel);
    }
}
