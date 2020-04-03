package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_REPORT_EXPENSE;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM_DATE;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TO_DATE;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.commons.core.date.DefiniteDate;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;

/** Get the latest expenses within a date range and output in report format */
public class ReportCommand extends Command {
    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " "
            + PREFIX_FROM_DATE
            + " FROM_DATE "
            + PREFIX_TO_DATE
            + " TO_DATE\n"
            + COMMAND_WORD
            + " "
            + PREFIX_DATE_BY_MONTH
            + " MONTH\n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + PREFIX_FROM_DATE
            + "14/2/2020 "
            + PREFIX_TO_DATE + "24/2/2020\n"
            + COMMAND_WORD
            + " "
            + PREFIX_DATE_BY_MONTH
            + "jan";

    public static final String MESSAGE_SUCCESS = MESSAGE_REPORT_EXPENSE;

    private final DateRange dateRange;

    public ReportCommand(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            if (dateRange.getStartDate().equals(DefiniteDate.MIN_DATE)) {
                dateRange.shiftStartDate(model.getUserPrefs().getDateFirstLaunched().get());
            }
        } catch (ParseException pe) {
            throw new CommandException(pe.getMessage());
        }

        model.loadFilteredTransactionsList();
        model.getReport().create(
                model.getFilteredTransactionsList(), purchase -> dateRange.contains(purchase.getDateAdded()));

        return new CommandResult(COMMAND_WORD,
                String.format(MESSAGE_SUCCESS,
                        dateRange.getStartDate().toString(), dateRange.getEndDate().toString()));
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
