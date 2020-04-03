package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.SelectItemCommand;

class SelectItemCommandParserTest {
    private SelectItemCommandParser parser = new SelectItemCommandParser();

    @BeforeEach
    public void setMainContext() {
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
    }

    @Test
    void parse_validArgs_returnsItemCommand() {
        assertParseSuccess(parser, "1 ", new SelectItemCommand(INDEX_FIRST_ITEM));
        assertParseSuccess(parser, "Combo Set", new SelectItemCommand("Combo Set"));
    }


    @Test
    void parse_invalidArgs_returnsItemCommand() {

    }

    @Test
    public void parse_invalidContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertParseFailure(parser, "select 1", ParserContext.INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext()
            + "\n" + ParserContext.SUGGESTED_CONTEXT_MESSAGE
            + ParserContext.STALL_CONTEXT);
    }
}
