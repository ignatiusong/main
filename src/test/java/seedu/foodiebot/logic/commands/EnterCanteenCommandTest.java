package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

class EnterCanteenCommandTest {
    private static Model model;
    private static Model expectedModel;
    private final String invalidCanteenName = "My House";
    private final String validCanteenName = "The Deck";

    @BeforeAll
    public static void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_success() {
        EnterCanteenCommand command = new EnterCanteenCommand(Index.fromOneBased(1));
        EnterCanteenCommand copy = new EnterCanteenCommand(Index.fromOneBased(1));
        EnterCanteenCommand command2 = new EnterCanteenCommand(Index.fromOneBased(2));
        assertEquals(command, command);

        assertEquals(command, copy);
        assertNotEquals(command, command2);

        SelectItemCommand otherCommandType = new SelectItemCommand(INDEX_FIRST_ITEM);
        assertFalse(command.equals(otherCommandType));

        assertCommandSuccess(new EnterCanteenCommand(Index.fromOneBased(1)),
                EnterCanteenCommand.COMMAND_WORD, model,
                EnterCanteenCommand.MESSAGE_SUCCESS, expectedModel);


        assertCommandSuccess(new EnterCanteenCommand(validCanteenName),
                EnterCanteenCommand.COMMAND_WORD, model,
                EnterCanteenCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
