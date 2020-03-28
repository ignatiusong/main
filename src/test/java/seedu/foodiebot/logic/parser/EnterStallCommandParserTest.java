package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.foodiebot.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.EnterStallCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

class EnterStallCommandParserTest {
    private static final String VALID_STALL_NAME = "Duck and Chicken Rice";
    private EnterStallCommandParser parser = new EnterStallCommandParser();

    @BeforeAll
    public static void setCanteenContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertThrows(ParseException.class, () -> new EnterStallCommandParser().parse("enter 1"));
        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
    }

    /*
    @Test
    public void parse_validIndex_returnsEnterStallCommand() {
        assertParseSuccess(parser, "1", new EnterStallCommand(INDEX_FIRST_ITEM));
    }*/

    @Test
    public void parse_invalidIndex_returnsEnterStallCommand() {
        //assertParseFailure(parser, "0", MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void parse_validName_returnsStallCommand() {
        assertParseSuccess(parser, VALID_STALL_NAME, new EnterStallCommand(VALID_STALL_NAME));
    }
}
