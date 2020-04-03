package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.FilterCommand;

class FilterCommandParserTest {
    private static final String VALID_TAG = "asian";
    private static final String VALID_TAG_CASE = "AsiaN";
    private static final String VALID_TAG_ALPHANUMERIC = "discount20";
    private static final String VALID_TAG_SYMBOLS = "asian-stall";
    private FilterCommandParser parser = new FilterCommandParser();

    @BeforeAll
    public static void setParserContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertDoesNotThrow(() -> new FilterCommandParser().parse("asian"));
        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        assertDoesNotThrow(() -> new FilterCommandParser().parse("asian"));
        assertEquals(ParserContext.getCurrentContext(), ParserContext.CANTEEN_CONTEXT);
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertDoesNotThrow(() -> new FilterCommandParser().parse("asian"));

        //assertThrows(IllegalArgumentException.class, () -> new FilterCommandParser().parse("asian"));
    }

    @Test
    public void parse_validName_returnsStallCommand() {
        assertParseSuccess(parser, VALID_TAG, new FilterCommand(VALID_TAG));
        assertParseSuccess(parser, VALID_TAG_CASE, new FilterCommand(VALID_TAG_CASE));
        assertParseSuccess(parser, VALID_TAG_ALPHANUMERIC, new FilterCommand(VALID_TAG_ALPHANUMERIC));
        assertParseSuccess(parser, VALID_TAG_SYMBOLS, new FilterCommand(VALID_TAG_SYMBOLS));
    }

    @Test
    public void parse_index() {
        assertParseSuccess(parser, "1", new FilterCommand(1));
    }
}
