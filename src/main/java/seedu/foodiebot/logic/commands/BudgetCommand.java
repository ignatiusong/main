package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_BUDGET_SET;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_BUDGET_VIEW;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;

import java.time.LocalDate;
import java.util.Objects;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.budget.Budget;

/** Manages the budget commands, e.g. view, set. */
public class BudgetCommand extends Command {
    public static final String COMMAND_WORD = "budget";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + " VIEW : View the budget. \n"
            + "SET : Set the budget"
            + "Parameters: "
            + PREFIX_DATE_BY_MONTH
            + "AMOUNT \n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + PREFIX_DATE_BY_MONTH
            + "100 ";

    public static final String MESSAGE_SET = MESSAGE_BUDGET_SET;
    public static final String MESSAGE_VIEW = MESSAGE_BUDGET_VIEW;
    public static final String MESSAGE_FAILURE = "No budget stored!";

    private final Budget budget;
    private final String action;

    public BudgetCommand(Budget budget, String action) {
        this.budget = budget;
        this.action = action;
    }

    public BudgetCommand(String action) {
        this.budget = new Budget();
        this.action = action;
    }

    /** A boolean check if the current system date falls inside the date range of the budget.
     * @param budget The budget object to check against.
     * @return true if the system date falls within the date range of the budget, false otherwise.
     */
    public static boolean systemDateIsInCycleRange(Budget budget) {
        return budget.getCycleRange().contains(LocalDate.now());
    }

    /** Helper function to write the budget to the model.
     * @param model The FoodieBot model.
     * @param budget The Budget object.
     * */
    public static void saveBudget(Model model, Budget budget) {
        model.setBudget(budget);
    }

    /** Helper function to read the budget from the model.
     * @param model The FoodieBot model.
     * @return The budget stored in the model if it is present, otherwise a new empty budget with
     * default values is created.
     */
    public static Budget loadBudget(Model model) {
        return model.getBudget().isPresent()
                ? model.getBudget().get()
                : new Budget();
    }

    /** Helper function to hold a successful return message for 'budget set'.
     * @param budget The budget object.
     * @return The command result.
     */
    public static CommandResult commandSetSuccess(Budget budget) {
        return new CommandResult(COMMAND_WORD, String.format(MESSAGE_SET,
                budget.getDurationAsString(), budget.getTotalBudget(), budget.getRemainingDailyBudget()));
    }

    /** Helper function to hold a successful return message for 'budget view'.
     * @param budget The budget object.
     * @return The command result.
     */
    public static CommandResult commandViewSuccess(Budget budget) {
        return new CommandResult(COMMAND_WORD, String.format(MESSAGE_VIEW,
                budget.getDurationAsString(), budget.getTotalBudget(), budget.getRemainingBudget(),
                budget.getRemainingDailyBudget(), budget.getDurationAsString()));
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (action.equals("set")) {
            saveBudget(model, budget);

            // Displays an empty list
            model.loadFilteredTransactionsList();
            model.updateFilteredTransactionsList(Objects::isNull);

            return commandSetSuccess(budget);

        } else {
            Budget savedBudget = loadBudget(model);

            DateRange budgetCycleRange = savedBudget.getCycleRange();
            model.loadFilteredTransactionsList();
            model.updateFilteredTransactionsList(purchase ->
                    budgetCycleRange.contains(purchase.getDateAdded())
                    && purchase.getDateAdded().atTime(purchase.getTimeAdded()).isAfter(
                            savedBudget.getDateTimeOfCreation()
                    ));

            if (!savedBudget.equals(new Budget())) {
                if (!systemDateIsInCycleRange(savedBudget)) {
                    savedBudget.resetRemainingBudget();
                }
                saveBudget(model, savedBudget);
                return commandViewSuccess(savedBudget);
            }

            return new CommandResult(COMMAND_WORD, MESSAGE_FAILURE);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BudgetCommand)) {
            return false;
        }

        BudgetCommand otherBudget = (BudgetCommand) other;
        return otherBudget.budget.getTotalBudget() == budget.getTotalBudget()
            && otherBudget.action.equals(action);
    }
}
