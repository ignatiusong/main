package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM;

import java.util.stream.Stream;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.EditCommand;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GoToCanteenCommand object
 */
public class GoToCanteenCommandParser implements Parser<GoToCanteenCommand> {
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
     * Parses the given {@code String} of arguments in the context of the GoToCanteenCommand and returns a
     * GoToCanteenCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoToCanteenCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FROM);

        if (!arePrefixesPresent(argMultimap, PREFIX_FROM)) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoToCanteenCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }


        String nearestBlockName = ParserUtil.parseBlockName(argMultimap.getValue(PREFIX_FROM).get());
        return new GoToCanteenCommand(index, nearestBlockName);
    }
}
