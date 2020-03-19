package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * .
 */
public class FavoritesCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FavoritesCommand and returns a
     * FavoritesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FavoritesCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String[] enteredText = argMultimap.getPreamble().split(" ");
        String action = enteredText[0];
        switch (action) {
        case "set":
            try {
                return new FavoritesCommand(ParserUtil.parseIndex(enteredText[1]));
            } catch (IndexOutOfBoundsException oobe) {
                break;
            }
        case "view":
            return new FavoritesCommand();
        default:
            break;
        }

        if (!ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
            throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoritesCommand.MESSAGE_USAGE));
    }
}
