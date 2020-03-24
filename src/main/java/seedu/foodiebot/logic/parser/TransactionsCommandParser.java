package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_WEEK;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_YEAR;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM_DATE;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TO_DATE;

import seedu.foodiebot.commons.core.date.ConceptualDate;
import seedu.foodiebot.commons.core.date.DateFormatter;
import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.commons.core.date.DateRangeStyle;
import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a new TransactionsCommand object */
public class TransactionsCommandParser implements Parser<TransactionsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TransactionsCommand and returns a
     * TransactionsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TransactionsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FROM_DATE, PREFIX_TO_DATE,
                PREFIX_DATE_BY_WEEK, PREFIX_DATE_BY_MONTH, PREFIX_DATE_BY_YEAR);

        if (argMultimap.size() == 1) {
            // The multimap only contains the preamble. No prefixes are present.
            return new TransactionsCommand(DateRange.generate());

        } else if (argMultimap.containsExact(PREFIX_FROM_DATE, PREFIX_TO_DATE)) {
            String start = getArgString(argMultimap, PREFIX_FROM_DATE);
            String end = getArgString(argMultimap, PREFIX_TO_DATE);
            return new TransactionsCommand(DateRange.of(start, end, DateRangeStyle.STRICT));

        } else if (argMultimap.containsExact(PREFIX_FROM_DATE)) {
            String start = getArgString(argMultimap, PREFIX_FROM_DATE);
            return new TransactionsCommand(DateRange.of(start, ConceptualDate.START_DATE));

        } else if (argMultimap.containsExact(PREFIX_TO_DATE)) {
            String end = getArgString(argMultimap, PREFIX_TO_DATE);
            return new TransactionsCommand(DateRange.of(end, ConceptualDate.END_DATE));

        } else if (argMultimap.containsExact(PREFIX_DATE_BY_WEEK)) {
            String date = getArgString(argMultimap, PREFIX_DATE_BY_WEEK);
            return new TransactionsCommand(DateRange.ofWeek(date, DateRangeStyle.STRICT));

        } else if (argMultimap.containsExact(PREFIX_DATE_BY_MONTH)) {
            String monthString = getArgString(argMultimap, PREFIX_DATE_BY_MONTH);
            int month = DateFormatter.formatMonth(monthString);
            return new TransactionsCommand(DateRange.ofMonth(month, DateRangeStyle.STRICT));

        } else if (argMultimap.containsExact(PREFIX_DATE_BY_YEAR)) {
            String yearString = getArgString(argMultimap, PREFIX_DATE_BY_YEAR);
            int year = DateFormatter.formatYear(yearString);
            return new TransactionsCommand(DateRange.ofYear(year, DateRangeStyle.STRICT));

        } else if (argMultimap.containsExact(PREFIX_DATE_BY_MONTH, PREFIX_DATE_BY_YEAR)) {
            String monthString = getArgString(argMultimap, PREFIX_DATE_BY_MONTH);
            String yearString = getArgString(argMultimap, PREFIX_DATE_BY_YEAR);
            int month = DateFormatter.formatMonth(monthString);
            int year = DateFormatter.formatYear(yearString);
            return new TransactionsCommand(DateRange.ofMonth(month, year, DateRangeStyle.STRICT));

        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TransactionsCommand.MESSAGE_USAGE));
        }
    }

    /** Extracts the argument tagged to the given prefix. Throws {@code} ParseException if no value is present.*/
    public static String getArgString(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        return argMultimap.getValue(prefix)
                .orElseThrow(() -> new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, TransactionsCommand.MESSAGE_USAGE)));
    }
}
