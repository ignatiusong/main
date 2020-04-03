package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.foodiebot.logic.commands.ReviewCommand.MESSAGE_FAILURE;
import static seedu.foodiebot.logic.commands.ReviewCommand.MESSAGE_USAGE;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.ReviewCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.rating.Review;

class ReviewCommandParserTest {
    private ReviewCommandParser parser = new ReviewCommandParser();

    @BeforeAll
    public static void setParserContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertThrows(ParseException.class, () -> new ReviewCommandParser().parse("1 my review"));
        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        assertThrows(ParseException.class, () -> new ReviewCommandParser().parse("1 my review"));
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertThrows(ParseException.class, () -> new ReviewCommandParser().parse("1 my review"));

        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        assertDoesNotThrow(() -> new ReviewCommandParser().parse("1 my review"));
    }

    @Test
    public void parse_validArgs_returnsReviewCommand() {
        assertParseSuccess(parser, "1 my review", new ReviewCommand(Index.fromOneBased(1),
            new Review("my review")));
    }

    @Test
    public void parse_invalidArgs_returnsReviewCommand() {
        assertParseFailure(parser, "0 my review", MESSAGE_FAILURE.concat(MESSAGE_USAGE));
        assertParseFailure(parser, "-1 my review", MESSAGE_FAILURE.concat(MESSAGE_USAGE));
        //assertThrows(Exception.class, () -> new ReviewCommandParser().parse("100 my review"));
        assertParseFailure(parser, "", MESSAGE_FAILURE.concat(MESSAGE_USAGE));
    }
}
