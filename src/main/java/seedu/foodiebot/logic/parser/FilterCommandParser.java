package seedu.foodiebot.logic.parser;

import seedu.foodiebot.logic.commands.FilterCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Parses a filter command to tell if it is valid
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EnterStallCommand and returns a
     * FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String enteredText = argMultimap.getPreamble();
        try {
            int price = ParserUtil.parseIndex(args).getOneBased();
            return new FilterCommand(price);
        } catch (ParseException e) {
            String tag = ParserUtil.parseFilterTag(enteredText);
            return new FilterCommand(tag);
        }

    }
}
