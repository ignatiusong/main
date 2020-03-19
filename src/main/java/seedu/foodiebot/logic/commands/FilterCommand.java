package seedu.foodiebot.logic.commands;

import seedu.foodiebot.model.Model;

/**
 * Filters a list of canteens or stalls to only display things which have a particular keyword
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "TAG \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "Western";

    public static final String MESSAGE_SUCCESS = "";

    private String tag;

    public FilterCommand(String tag) {
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
