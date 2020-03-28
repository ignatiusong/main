package seedu.foodiebot.logic.commands;

import java.util.Optional;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;

/** Terminates the program. */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Exiting Address Book as requested ...";

    public static final String CHANGE_CONTEXT_ACKNOWLEDGEMENT =
            "Exited from menu : ";

    public static final String MESSAGE_EXIT_FAVORITES =
        "Exited from favorites : ";

    @Override
    public CommandResult execute(Model model) {
        String context = ParserContext.getCurrentContext();
        switch (context) {
        case ParserContext.CANTEEN_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            ParserContext.setCurrentCanteen(Optional.empty());
            return new CommandResult(COMMAND_WORD, CHANGE_CONTEXT_ACKNOWLEDGEMENT + context,
                false, false);

        case ParserContext.STALL_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
            ParserContext.setCurrentStall(Optional.empty());
            return new CommandResult(COMMAND_WORD, CHANGE_CONTEXT_ACKNOWLEDGEMENT + context,
                false, false);

        case ParserContext.FAVORITE_CONTEXT:
            return new CommandResult(COMMAND_WORD, MESSAGE_EXIT_FAVORITES + context,
                false, false);

        case ParserContext.TRANSACTIONS_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            ParserContext.setCurrentCanteen(Optional.empty());
            return new CommandResult(COMMAND_WORD, CHANGE_CONTEXT_ACKNOWLEDGEMENT + context,
                    false, false);

        default:
            return new CommandResult(COMMAND_WORD, MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        }
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
