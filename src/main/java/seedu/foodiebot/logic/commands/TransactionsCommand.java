package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_REPORT_TRANSACTIONS;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM_DATE;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TO_DATE;

import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;

/** Gets the latest food transactions where reviews and single-user ratings can be added. */
public class TransactionsCommand extends Command {
    public static final String COMMAND_WORD = "transactions";

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

    public static final String MESSAGE_SUCCESS = MESSAGE_REPORT_TRANSACTIONS;
    private static final Logger logger = LogsCenter.getLogger(TransactionsCommand.class);

    private final DateRange dateRange;

    public TransactionsCommand(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ParserContext.setCurrentContext(ParserContext.TRANSACTIONS_CONTEXT);
        logger.info("Enter transactions");

        model.loadFilteredTransactionsList();
        model.updateFilteredTransactionsList(purchase -> dateRange.contains(purchase.getDateAdded()));

        return new CommandResult(COMMAND_WORD,
                String.format(MESSAGE_SUCCESS,
                        dateRange.getStartDate().toString(), dateRange.getEndDate().toString()));
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
