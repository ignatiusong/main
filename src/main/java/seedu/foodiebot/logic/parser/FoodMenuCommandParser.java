package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.FoodMenuCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a new FoodMenuCommand object */
public class FoodMenuCommandParser implements Parser<FoodMenuCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FoodMenuCommand and returns a
     * FoodMenuCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FoodMenuCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FoodMenuCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FoodMenuCommand();
    }
}
