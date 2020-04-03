package seedu.foodiebot.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.parser.exceptions.ParseException;

class TransactionsCommandParserTest {
    private TransactionsCommandParser parser = new TransactionsCommandParser();

    @Test
    void parse() throws ParseException {
        /*assertParseSuccess(parser, "f/2020-02-20 t/2020-02-25",
                new TransactionsCommand(DateRange.of(
                        LocalDate.of(2020, 2, 20),
                        LocalDate.of(2020, 2, 25))));*/
    }

    @Test
    void getArgString() {
        /*assertParseFailure(
                parser,
                "fasfa/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetCommand.MESSAGE_USAGE));*/
    }
}
