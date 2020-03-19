package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_DAY;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_WEEK;

import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.budget.Budget;

/** Parses input arguments and creates a new BudgetCommand object */
public class BudgetCommandParser implements Parser<BudgetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BudgetCommand and returns a
     * BudgetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public BudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE_BY_DAY,
                PREFIX_DATE_BY_WEEK, PREFIX_DATE_BY_MONTH);

        String action = argMultimap.getPreamble();
        switch(action) {
        case "set":
            try {
                Budget budget = setBudget(argMultimap);
                return new BudgetCommand(budget, action);
            } catch (IndexOutOfBoundsException oobe) {
                break;
            }

        case "view":
            return new BudgetCommand(action);
        default:
            break;
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetCommand.MESSAGE_USAGE));
    }

    /**
     * Sets the budget to the specified values given by the user.
     * @param argMultimap the map storing all arguments by the user.
     * @return a new Budget object.
     * @throws ParseException if any input by the user is unable to be parsed into the Budget object.
     */
    public Budget setBudget(ArgumentMultimap argMultimap) throws ParseException {

        if (argMultimap.size(false) == 1 && argMultimap.containsAny(
                PREFIX_DATE_BY_DAY, PREFIX_DATE_BY_WEEK, PREFIX_DATE_BY_MONTH)) {

            try {
                Prefix firstPrefix = argMultimap.prefixSet()
                        .stream()
                        .findFirst()
                        .get();
                String argValue = getArgString(argMultimap, firstPrefix);
                float value = Float.parseFloat(argValue);
                return new Budget(value, firstPrefix.toString());

            } catch (NullPointerException | NumberFormatException ne) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetCommand.MESSAGE_USAGE));
            }

        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetCommand.MESSAGE_USAGE));
    }

    /** Extracts the argument tagged to the given prefix. Throws {@code ParseException}
     * if no value is present.
     * */
    public static String getArgString(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        return argMultimap.getValue(prefix)
                .orElseThrow(() -> new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, BudgetCommand.MESSAGE_USAGE)));
    }
}
