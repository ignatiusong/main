package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.model.budget.Budget;

class BudgetCommandParserTest {
    private BudgetCommandParser parser = new BudgetCommandParser();

    @Test
    void parse_validArgs_returnsBudgetCommand() {
        Budget budget = new Budget(15, PREFIX_DATE_BY_MONTH.toString());
        assertParseSuccess(parser, "set m/15 ", new BudgetCommand(budget, "set"));
    }

    @Test
    void setBudget_invalid() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            BudgetCommand.MESSAGE_USAGE));
    }

    @Test
    void getArgString() {
        assertParseFailure(parser, "fasfa/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            BudgetCommand.MESSAGE_USAGE));
    }
}
