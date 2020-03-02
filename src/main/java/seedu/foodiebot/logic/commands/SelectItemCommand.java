package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;

/** Select the current list view item. */
public class SelectItemCommand extends Command {
    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = "";

    public static final String MESSAGE_SUCCESS = "";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodieBot(new FoodieBot());
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
