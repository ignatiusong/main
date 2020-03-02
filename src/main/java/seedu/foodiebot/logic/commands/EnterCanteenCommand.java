package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;

/** Selects a canteen to view the food stalls. */
public class EnterCanteenCommand extends Command {
    public static final String COMMAND_WORD = "enter";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD
            + "Parameters: "
            + "CANTEEN_NAME \n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + "deck ";

    public static final String MESSAGE_SUCCESS = "";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodieBot(new FoodieBot());
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
