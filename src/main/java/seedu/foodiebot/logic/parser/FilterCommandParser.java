package seedu.foodiebot.logic.parser;

import java.util.stream.Stream;

import seedu.foodiebot.logic.commands.FilterCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Parses a filter command to tell if it is valid
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(
            ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EnterStallCommand and returns a
     * FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String enteredText = argMultimap.getPreamble();
        String tag = ParserUtil.parseFilterTag(enteredText);
        return new FilterCommand(tag);

    }
}
