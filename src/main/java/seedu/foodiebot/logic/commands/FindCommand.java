package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.NameContainsKeywordsPredicate;

/**
 * Finds and lists all canteens in FoodieBot whose block name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Finds all canteens from given block name (case-insensitive)"
                    + "and displays them as a list with index numbers.\n"
                    + "Parameters: block_name\n"
                    + "Example: "
                    + COMMAND_WORD
                    + " com1 ";


    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCanteenList(predicate);
        return new CommandResult(COMMAND_WORD,
                String.format(
                        Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredCanteenList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                        && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
