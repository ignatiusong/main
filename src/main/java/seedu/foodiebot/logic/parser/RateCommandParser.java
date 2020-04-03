package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.logic.commands.RateCommand.MESSAGE_FAILURE;
import static seedu.foodiebot.logic.commands.RateCommand.MESSAGE_USAGE;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.RateCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.rating.Rating;

/**
 * Sets a rating for a purchased food.
 */
public class RateCommandParser implements Parser<RateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RatingCommandParser and returns a
     * SelectItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (!ParserContext.getCurrentContext().equals(ParserContext.TRANSACTIONS_CONTEXT)) {
            throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext()
                    + "\n" + ParserContext.SUGGESTED_CONTEXT_MESSAGE
                    + ParserContext.TRANSACTIONS_CONTEXT);
        }
        Index index;
        Rating rating;
        String[] enteredText = argMultimap.getPreamble().split("\\s+");
        try {
            index = Index.fromOneBased(Integer.parseInt(enteredText[0]));
            rating = new Rating(Integer.parseInt(enteredText[1]));
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_FAILURE + MESSAGE_USAGE);
        }
        return new RateCommand(index, rating);
    }

}
