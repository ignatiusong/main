package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.commands.EnterStallCommand;
import seedu.foodiebot.logic.commands.ExitCommand;
import seedu.foodiebot.logic.commands.FilterCommand;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.logic.commands.HelpCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.RandomizeCommand;
import seedu.foodiebot.logic.commands.RateCommand;
import seedu.foodiebot.logic.commands.ReportCommand;
import seedu.foodiebot.logic.commands.ReviewCommand;
import seedu.foodiebot.logic.commands.SelectItemCommand;
import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.util.SampleDataUtil;

public class FoodieBotParserTest {

    private final FoodieBotParser parser = new FoodieBotParser();

    @Test
    public void parseCommand_goToCanteen() throws Exception {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertTrue(parser.parseCommand(GoToCanteenCommand.COMMAND_WORD + " 1 f/com1") instanceof GoToCanteenCommand);
    }

    @Test
    public void parseCommand_enterCanteenOrStall() throws Exception {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertTrue(parser.parseCommand(EnterCanteenCommand.COMMAND_WORD + " The Deck") instanceof EnterCanteenCommand);
        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        ParserContext.setCanteenContext(SampleDataUtil.getSampleCanteens()[0]);
        assertTrue(parser.parseCommand(EnterStallCommand.COMMAND_WORD + " 1") instanceof EnterStallCommand);

    }

    @Test
    public void parseCommand_selectItem() throws Exception {
        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertTrue(parser.parseCommand(SelectItemCommand.COMMAND_WORD + " 1") instanceof SelectItemCommand);
    }

    @Test
    public void parseCommand_budget() throws Exception {
        assertTrue(parser.parseCommand(BudgetCommand.COMMAND_WORD + " set m/1") instanceof BudgetCommand);
    }

    @Test
    public void parseCommand_report() throws Exception {
        assertTrue(parser.parseCommand(ReportCommand.COMMAND_WORD + " ") instanceof ReportCommand);
    }

    @Test
    public void parseCommand_randomize() throws Exception {
        assertTrue(parser.parseCommand(RandomizeCommand.COMMAND_WORD + " ") instanceof RandomizeCommand);
    }

    @Test
    public void parseCommand_filter() throws Exception {
        assertTrue(parser.parseCommand(FilterCommand.COMMAND_WORD + " asian") instanceof FilterCommand);
    }

    @Test
    public void parseCommand_transactions() throws Exception {
        assertTrue(parser.parseCommand(TransactionsCommand.COMMAND_WORD + " ") instanceof TransactionsCommand);
    }

    @Test
    public void parseCommand_rate() throws Exception {
        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        assertTrue(parser.parseCommand(RateCommand.COMMAND_WORD + " 1 7") instanceof RateCommand);
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertThrows(ParseException.class, () -> parser.parseCommand(RateCommand.COMMAND_WORD + " 1 7"));
    }

    @Test
    public void parseCommand_review() throws Exception {
        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        assertTrue(parser.parseCommand(ReviewCommand.COMMAND_WORD + " 1 review") instanceof ReviewCommand);
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertThrows(ParseException.class, () -> parser.parseCommand(ReviewCommand.COMMAND_WORD + " 1 review"));
    }


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
            ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
            ParseException.class,
            MESSAGE_UNKNOWN_COMMAND, (
            ) -> parser.parseCommand("unknownCommand"));
    }
}
