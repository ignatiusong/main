package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.transaction.PurchasedFood;

/** Set a rating to a purchased food */
public class RateCommand extends Command {
    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "FOOD_INDEX, RATING \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "1 7";;
    public static final String MESSAGE_SUCCESS = "You have rated %s as %s\n";
    public static final String MESSAGE_FAILURE = "Invalid parameters!";

    private static final Logger logger = LogsCenter.getLogger(RateCommand.class);

    private final Optional<Index> index;
    private final Optional<Rating> rating;

    public RateCommand(Index index, Rating rating) {
        requireNonNull(index);
        requireNonNull(rating);
        this.index = Optional.of(index);
        this.rating = Optional.of(rating);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.loadFilteredTransactionsList();

        if (index.isPresent() && rating.isPresent()) {
            PurchasedFood food = model.getFoodieBot()
                    .getTransactionsList()
                    .get(index.get().getZeroBased());

            food.setRating(rating.get());

            return new CommandResult(COMMAND_WORD, String.format(
                    MESSAGE_SUCCESS, food.getName(), food.getRating().toString()));
        } else {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean needToSaveCommand() {
        return true;
    }

}
