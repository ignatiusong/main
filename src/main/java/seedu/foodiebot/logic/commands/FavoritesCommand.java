package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;

/** Displays the food items favorited by the user. */
public class FavoritesCommand extends Command {
    public static final String COMMAND_WORD = "favorites";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all favorites";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodieBot(new FoodieBot());
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
