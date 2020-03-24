package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.model.canteen.Block;
import seedu.foodiebot.model.canteen.Canteen;

class GoToCanteenCommandParserTest {
    private GoToCanteenCommandParser parser = new GoToCanteenCommandParser();

    @BeforeAll
    public static void setMainContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
    }

    @Test
    public void parse_validArgs_returnsGoToCanteenCommand() {
        assertParseSuccess(parser, "1 f/com1", new GoToCanteenCommand(Index.fromOneBased(1),
            "com1"));
        assertParseSuccess(parser, "The Deck f/com1", new GoToCanteenCommand(Index.fromOneBased(1),
            "com1"));
    }

    @Test
    public void parse_invalidArgs_returnsGoToCanteenCommand() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GoToCanteenCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a f/com1", Canteen.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "The Deck", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GoToCanteenCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "The Deck f/asf", Block.MESSAGE_CONSTRAINTS);
    }
}
