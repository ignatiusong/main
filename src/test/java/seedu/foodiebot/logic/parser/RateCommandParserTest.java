package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.foodiebot.logic.commands.RateCommand.MESSAGE_FAILURE;
import static seedu.foodiebot.logic.commands.RateCommand.MESSAGE_USAGE;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.parser.exceptions.ParseException;

class RateCommandParserTest {
    private RateCommandParser parser = new RateCommandParser();

    @BeforeAll
    public static void setParserContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertThrows(ParseException.class, () -> new RateCommandParser().parse("1 5"));
        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        assertThrows(ParseException.class, () -> new RateCommandParser().parse("1 5"));
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertThrows(ParseException.class, () -> new RateCommandParser().parse("1 5"));

        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        assertDoesNotThrow(() -> new RateCommandParser().parse("1 5"));
    }

    @Test
    public void parse_validArgs_returnsRateCommand() {
        //assertParseSuccess(parser, "1 5", new RateCommand(Index.fromOneBased(1),
        //    new Rating(5)));
    }

    @Test
    public void parse_invalidArgs_returnsRateCommand() {
        assertParseFailure(parser, "0 review", MESSAGE_FAILURE + MESSAGE_USAGE);
        assertParseFailure(parser, "-1 review", MESSAGE_FAILURE + MESSAGE_USAGE);
        assertParseFailure(parser, "", MESSAGE_FAILURE + MESSAGE_USAGE);
    }
}
