package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;

import java.util.stream.Stream;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.EnterStallCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Parses a enter stall command to tell if it is valid
 */
public class EnterStallCommandParser implements Parser<EnterStallCommand> {
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
     * EnterStallCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EnterStallCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String enteredText = argMultimap.getPreamble();

        Index index;

        if (!ParserContext.getCurrentContext().equals(ParserContext.CANTEEN_CONTEXT)) {
            throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
        }

        try {
            index = ParserUtil.parseStallIndex(enteredText, ParserContext.getCurrentCanteen().get());
            return new EnterStallCommand(index);
        } catch (Exception ex) {
            if (ex instanceof IndexOutOfBoundsException) {
                throw new ParseException(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
            }
            return new EnterStallCommand(enteredText);
        }
    }
}

