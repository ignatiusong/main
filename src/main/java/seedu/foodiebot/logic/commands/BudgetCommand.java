package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_BUDGET_VIEW;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;

/** Manages the budget commands, e.g. view, set. */
public class BudgetCommand extends Command {
    public static final String COMMAND_WORD = "budget";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + "VIEW : View the budget. \n"
            + "SET : Set the budget"
            + "Parameters: "
            + PREFIX_DATE_BY_MONTH
            + "AMOUNT \n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + PREFIX_DATE_BY_MONTH
            + "100 ";

    public static final String MESSAGE_SUCCESS = MESSAGE_BUDGET_VIEW;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFoodieBot(new FoodieBot());
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
}
