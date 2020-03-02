package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a new TransactionsCommand object */
public class TransactionsCommandParser implements Parser<TransactionsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TransactionsCommand and returns a
     * TransactionsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TransactionsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TransactionsCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new TransactionsCommand();
    }
}
