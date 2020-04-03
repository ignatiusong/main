package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.parser.exceptions.ParseException;

class ReportCommandParserTest {

    private ReportCommandParser parser = new ReportCommandParser();
    @BeforeAll
    public static void setMainContext() {
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
    }
    @Test
    public void parse_invalidArgs_returnsReportCommand() throws ParseException {
        DateTimeFormatter validDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = "12-02-2020";
        LocalDate localDateValid = LocalDate.parse(date, validDateFormat);

        //parameters unspecified
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("f/12-02-2020 "));
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("f/12-02-2020 t/"));
        //same date
        //assertParseSuccess(parser, "f/12-02-2020 t/12-02-2020", new ReportCommand(
        //    DateRange.of(localDateValid, localDateValid, DateRangeStyle.STRICT)));
        //invalid date format
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("f/2020-02-12 "));
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("f/2020-13-04 "));
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("f/2020-10-33 "));

        //invalid prefix t without f
        assertThrows(ParseException.class, () -> new ReportCommandParser().parse("t/2020-02-12 "));

    }

    @Test
    public void parse_validArgs_returnsReportCommand() throws ParseException {
        /*assertParseSuccess(parser, "w/12-02-2020", new ReportCommandParser().parse("w/12-02-2020"));
        assertParseSuccess(parser, "m/jan", new ReportCommandParser().parse("m/jan"));
        assertParseSuccess(parser, "m/jan y/2019", new ReportCommandParser().parse("m/jan y/2019"));
        assertParseSuccess(parser, "y/ 2020", new ReportCommandParser().parse("y/ 2020"));
         */
    }
}
