package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.EnterCanteenCommand;

class EnterCanteenCommandParserTest {

    private EnterCanteenCommandParser parser = new EnterCanteenCommandParser();

    @BeforeEach
    public void setMainContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
    }

    @Test
    public void parse_validIndex_returnsEnterCanteenCommand() {
        assertParseSuccess(parser, "1", new EnterCanteenCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_invalidIndex_returnsEnterCanteenCommand() {
        assertParseFailure(
            parser,
            "0",
            MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void parse_validName_returnsCanteenCommand() {
        assertParseSuccess(parser, "The Deck", new EnterCanteenCommand("The Deck"));
    }

    @Test
    public void parse_invalidContext() {
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertParseFailure(parser, "1", ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext()
            + "\n" + ParserContext.SUGGESTED_CONTEXT_MESSAGE
            + ParserContext.MAIN_CONTEXT + ", " + ParserContext.CANTEEN_CONTEXT);
    }
}
