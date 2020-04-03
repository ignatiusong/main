package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;

class FavoritesCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_viewFavorite_success() {
        assertCommandSuccess(new FavoritesCommand("view"), FavoritesCommand.COMMAND_WORD, model,
            FavoritesCommand.MESSAGE_VIEW_SUCCESS, expectedModel);


        assertCommandSuccess(new FavoritesCommand("set 1"), FavoritesCommand.COMMAND_WORD, model,
            FavoritesCommand.MESSAGE_VIEW_SUCCESS, expectedModel);


        assertCommandSuccess(new FavoritesCommand("remove"), FavoritesCommand.COMMAND_WORD, model,
            FavoritesCommand.MESSAGE_VIEW_SUCCESS, expectedModel);

        FavoritesCommand command = new FavoritesCommand("view");
        SelectItemCommand otherCommandType = new SelectItemCommand(INDEX_FIRST_ITEM);
        assertFalse(new FavoritesCommand("view").equals(otherCommandType));
        assertTrue(command.equals(command));
    }

    @Test
    void execute_modifyFavorite_success() {
        expectedModel.setFavorite(SampleDataUtil.getSampleFoods()[0]);
        assertCommandSuccess(new FavoritesCommand(INDEX_FIRST_ITEM, "set"), FavoritesCommand.COMMAND_WORD, model,
            String.format(FavoritesCommand.MESSAGE_SET_SUCCESS,
                SampleDataUtil.getSampleFoods()[0].getName()), expectedModel);
        expectedModel.removeFavorite(SampleDataUtil.getSampleFoods()[0]);
        assertCommandSuccess(new FavoritesCommand(INDEX_FIRST_ITEM, "remove"), FavoritesCommand.COMMAND_WORD, model,
            FavoritesCommand.MESSAGE_VIEW_SUCCESS, expectedModel);

    }
}
