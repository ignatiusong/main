package seedu.foodiebot.commons.core.date;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Formats an input String into a LocalDate object */
public class DateFormatter {

    /** A list of valid date formats. */
    public static Stream<DateTimeFormatter> dateFormats() {
        return Stream.of(DateTimeFormatter.ofPattern("d.M.uu"),
                DateTimeFormatter.ofPattern("d.M.uuuu"),
                DateTimeFormatter.ofPattern("d-M-uu"),
                DateTimeFormatter.ofPattern("d-M-uuuu"),
                DateTimeFormatter.ofPattern("d/M/uu"),
                DateTimeFormatter.ofPattern("d/M/uuuu")
        );
    }

    /** A list of valid month formats. */
    public static List<String> monthFormats() {
        return List.of("JAN", "FEB", "MAR",
                "APR", "MAY", "JUN",
                "JUL", "AUG", "SEP",
                "OCT", "NOV", "DEC",
                "JANUARY", "FEBRUARY", "MARCH",
                "APRIL", "JUNE",
                "JULY", "AUGUST", "SEPTEMBER",
                "OCTOBER", "NOVEMBER", "DECEMBER");
    }

    /**
     * Converts a String representation of a date into a LocalDate object using a strict format resolver.
     * @param dateString the input String to format.
     * @return a LocalDate representation of the input String.
     * @throws ParseException if all possible DateTimeFormatter patterns are unable to format
     * the String into a LocalDate object.
     */
    public static LocalDate formatDate(String dateString) throws ParseException {
        return dateFormats()
                .map(formatter -> formatDateHelper(dateString, formatter.withResolverStyle(ResolverStyle.STRICT)))
                .reduce((a, b) -> a.equals(Optional.empty()) ? b : a)
                .get()
                .orElseThrow(() -> new ParseException(MESSAGE_INVALID_DATE));
    }

    /** A helper function for formatDate() to parse a string to a LocalDate object with the given formatter.
     * Returns a LocalDate object encapsulated in an Optional if successful, otherwise an empty Optional
     * if the parser is unable to parse the date with the given formatter.
     * @param date the input String to format.
     * @param formatter the DateTimeFormatter to format the date with the required style.
     * @return an Optional describing the date in a LocalDate object, or an empty Optional if this formatter
     * is unable to parse the date.
     */
    public static Optional<LocalDate> formatDateHelper(String date, DateTimeFormatter formatter) {
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            return Optional.ofNullable(localDate);
        } catch (DateTimeParseException dtpe) {
            return Optional.empty();
        }
    }

    /** Formats the given String month into an integer. Throws a ParseException if the supplied input
     * is not a valid month.
     * @param month the input month in a String format.
     * @return an integer representation of the month.
     * @throws ParseException if the month cannot be formatted.
     */
    public static int formatMonth(String month) throws ParseException {
        String monthString = month.toUpperCase();
        List<String> monthFormats = monthFormats();

        if (monthFormats.contains(monthString)) {
            int monthIndex = monthFormats.indexOf(monthString.substring(0, 3));
            return monthIndex + 1;
        }
        throw new ParseException(MESSAGE_INVALID_DATE);
    }

    /** Formats the given String year into an integer. Throws a ParseException if the supplied year is not
     * a valid year.
     * @param year the input year in a String format.
     * @return an integer representation of the year.
     * @throws ParseException if the year cannot be formatted.
     */
    public static int formatYear(String year) throws ParseException {
        try {
            return Integer.parseInt(year);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
    }
}
