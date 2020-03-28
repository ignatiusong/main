package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.FavoritesCommand;

class FavoritesCommandParserTest {
    private FavoritesCommandParser parser = new FavoritesCommandParser();

    @BeforeAll
    public static void setStallContext() {
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
    }

    @Test
    void parse_validArgs_returnsFavoritesCommand() {
        assertParseSuccess(parser, "set 1", new FavoritesCommand(INDEX_FIRST_ITEM, "set"));
        assertParseSuccess(parser, "view", new FavoritesCommand("view"));
    }

    @Test
    void parse_invalidArgs_displaysInvalidCommandFormat() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FavoritesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "set", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FavoritesCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "set 0", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
