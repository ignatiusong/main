package seedu.foodiebot.logic.parser;

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
            throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
        }

        String[] enteredText = argMultimap.getPreamble().split(" ");
        Index index = Index.fromOneBased(Integer.parseInt(enteredText[0]));
        Rating rating;
        try {
            rating = new Rating(Integer.parseInt(enteredText[1]));
        } catch (IllegalArgumentException iae) {
            throw new ParseException("Rating must be a whole number from 0 to 10!");
        }


        return new RateCommand(index, rating);
    }

}
