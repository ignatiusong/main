package seedu.foodiebot.logic.commands;

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

    public static final String MESSAGE_EXIT_RANDOMIZE =
            "Exit Randomize. ";

    @Override
    public CommandResult execute(Model model) {
        String context = ParserContext.getCurrentContext();
        switch (context) {
        case ParserContext.CANTEEN_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            ParserContext.setCanteenContext(null);
            return new CommandResult(COMMAND_WORD, CHANGE_CONTEXT_ACKNOWLEDGEMENT + context,
                false, false);

        case ParserContext.STALL_CONTEXT:
            if (ParserContext.getPreviousContext().equals(ParserContext.RANDOMIZE_CONTEXT)) {
                ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            } else {
                ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
                ParserContext.setStallContext(null);
            }
            return new CommandResult(COMMAND_WORD, CHANGE_CONTEXT_ACKNOWLEDGEMENT + context,
                false, false);

        case ParserContext.FAVORITE_CONTEXT:
            return new CommandResult(COMMAND_WORD, MESSAGE_EXIT_FAVORITES + context,
                false, false);

        case ParserContext.RANDOMIZE_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            return new CommandResult(COMMAND_WORD, MESSAGE_EXIT_RANDOMIZE, false, false);

        case ParserContext.TRANSACTIONS_CONTEXT:
            ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
            ParserContext.setCanteenContext(null);
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
