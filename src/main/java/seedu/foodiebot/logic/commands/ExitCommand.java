package seedu.foodiebot.logic.commands;

import seedu.foodiebot.model.Model;

/** Terminates the program. */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT =
            "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(COMMAND_WORD, MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);

    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
