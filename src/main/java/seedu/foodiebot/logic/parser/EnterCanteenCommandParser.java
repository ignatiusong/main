package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a new AddCommand object */
public class EnterCanteenCommandParser implements Parser<EnterCanteenCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EnterCanteenCommand and returns a
     * EnterCanteenCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EnterCanteenCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterCanteenCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new EnterCanteenCommand();
    }
}
