package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.ReportCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a new ReportCommand object */
public class ReportCommandParser implements Parser<ReportCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReportCommand and returns a
     * ReportCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReportCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ReportCommand();
    }
}
