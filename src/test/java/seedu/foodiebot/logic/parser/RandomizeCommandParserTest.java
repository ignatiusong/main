package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.RandomizeCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.randomize.Randomize;

class RandomizeCommandParserTest {
    private RandomizeCommandParser parser = new RandomizeCommandParser();
    private Randomize randomize = Randomize.checkRandomize();
    @BeforeAll
    public static void setMainContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
    }

    @Test
    public void parse_validArgs_returnsRandomizeCommand() {
        assertParseSuccess(parser, "", new RandomizeCommand("", "all", randomize));
        //assertParseFailure(parser, "asf", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        //    RandomizeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_returnsRandomizeCommand() throws ParseException {
        assertParseSuccess(parser, "c/The Deck", new RandomizeCommandParser().parse("c/The Deck"));

        //assertParseFailure(parser, "c/", MESSAGE_INVALID_CANTEEN_NAME);
        //assertParseFailure(parser, "c/a", MESSAGE_INVALID_CANTEEN_NAME);
    }
}
