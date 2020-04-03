package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.logic.commands.ExitCommand.COMMAND_WORD;
import static seedu.foodiebot.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult =
            new CommandResult(COMMAND_WORD, MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);

        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);

        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);

        ParserContext.setCurrentContext(ParserContext.FAVORITE_CONTEXT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);

        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);

        assertFalse(new ExitCommand().needToSaveCommand());
    }
}
