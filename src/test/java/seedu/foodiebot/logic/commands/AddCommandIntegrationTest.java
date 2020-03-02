package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.BeforeEach;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
    }

    /*
    @Test
    public void execute_newPerson_success() {
        Canteen validCanteen = new CanteenBuilder().build();

        Model expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
        expectedModel.addCanteen(validCanteen);

        assertCommandSuccess(
                new AddCommand(validCanteen),
                model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCanteen),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Canteen personInList = model.getFoodieBot().getCanteenList().get(0);
        assertCommandFailure(
                new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

     */
}
