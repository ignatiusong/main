package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.FavoritesCommand;

class FavoritesCommandParserTest {
    private FavoritesCommandParser parser = new FavoritesCommandParser();

    @BeforeEach
    public void setStallContext() {
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
    }

    @Test
    void parse_validArgs_returnsFavoritesCommand() {
        assertParseSuccess(parser, "set 1", new FavoritesCommand(INDEX_FIRST_ITEM, "set"));
        assertParseSuccess(parser, "view", new FavoritesCommand("view"));
        assertParseSuccess(parser, "remove 1", new FavoritesCommand(INDEX_FIRST_ITEM, "remove"));
    }

    @Test
    void parse_invalidArgs_displaysInvalidCommandFormat() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FavoritesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "set", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FavoritesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "set 0", ParserUtil.MESSAGE_INVALID_INDEX);
    }
    @Test
    public void parse_invalidContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertParseFailure(parser, "set 1", ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext()
            + "\n" + ParserContext.SUGGESTED_CONTEXT_MESSAGE
            + ParserContext.STALL_CONTEXT);
    }
}
