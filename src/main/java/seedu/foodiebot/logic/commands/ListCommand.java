package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.model.Model.PREDICATE_SHOW_ALL_CANTEENS;

import seedu.foodiebot.model.Model;

/** Lists all persons in the address book to the user. */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all canteens";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCanteenList(PREDICATE_SHOW_ALL_CANTEENS);
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
