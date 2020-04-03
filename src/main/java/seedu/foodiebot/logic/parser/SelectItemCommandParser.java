package seedu.foodiebot.logic.parser;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.SelectItemCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Parses a enter stall command to tell if it is valid
 */
public class SelectItemCommandParser implements Parser<SelectItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SelectItemCommand and returns a
     * SelectItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectItemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        String enteredText = argMultimap.getPreamble();
        Index index = null;

        if (!ParserContext.getCurrentContext().equals(ParserContext.STALL_CONTEXT)) {
            throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext()
                    + "\n" + ParserContext.SUGGESTED_CONTEXT_MESSAGE
                    + ParserContext.STALL_CONTEXT);
        }

        try {
            index = ParserUtil.parseIndex(enteredText);
            return new SelectItemCommand(index);
        } catch (ParseException pe) {
            return new SelectItemCommand(enteredText);
        }
    }
}
