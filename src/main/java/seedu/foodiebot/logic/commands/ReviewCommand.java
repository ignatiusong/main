package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;

/** Set a review to a purchased food*/
public class ReviewCommand extends Command {
    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "FOOD_INDEX, REVIEW \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "1 This is a review.";;
    public static final String MESSAGE_SUCCESS = "You have reviewed %s: \n%s \n";
    public static final String MESSAGE_FAILURE = "Invalid parameters!";

    private static final Logger logger = LogsCenter.getLogger(ReviewCommand.class);

    private final Optional<Index> index;
    private final Optional<Review> review;

    public ReviewCommand(Index index, Review review) {
        requireNonNull(index);
        requireNonNull(review);
        this.index = Optional.of(index);
        this.review = Optional.of(review);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.loadFilteredTransactionsList();

        if (index.isPresent() && review.isPresent()) {
            PurchasedFood food = model.getFoodieBot()
                    .getTransactionsList()
                    .get(index.get().getZeroBased());

            food.setReview(review.get());

            return new CommandResult(COMMAND_WORD, String.format(
                    MESSAGE_SUCCESS, food.getName(), food.getReview().toString()));
        } else {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean needToSaveCommand() {
        return true;
    }

}
