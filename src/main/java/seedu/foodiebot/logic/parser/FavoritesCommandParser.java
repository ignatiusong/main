package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * .
 */
public class FavoritesCommandParser implements Parser<FavoritesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavoritesCommand and returns a
     * FavoritesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FavoritesCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        String[] enteredText = argMultimap.getPreamble().split("\\s+");
        String action = enteredText[0];

        switch (action) {
        case "set":
            if (!ParserContext.getCurrentContext().equals(ParserContext.STALL_CONTEXT)) {
                throw new ParseException(ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
            }

            try {
                return new FavoritesCommand(ParserUtil.parseIndex(enteredText[1]), "set");
            } catch (IndexOutOfBoundsException oobe) {
                break;
            }
        case "remove":
            return new FavoritesCommand("remove");
        case "view":
            return new FavoritesCommand("view");
        default:
            break;
        }


        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoritesCommand.MESSAGE_USAGE));
    }
}
