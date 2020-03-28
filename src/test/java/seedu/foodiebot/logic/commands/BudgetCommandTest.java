package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.BudgetCommand.MESSAGE_VIEW;
import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.budget.Budget;

class BudgetCommandTest {
    private LocalDate startDate = LocalDate.of(2020, 1, 20);
    private LocalDate endDate = LocalDate.of(2020, 2, 20);
    private DateRange dr = DateRange.of
            (startDate, endDate);
    private Budget budget = new Budget(15, 10, "daily", LocalDateTime.now(), dr);
    private BudgetCommand budgetView = new BudgetCommand("view");
    private BudgetCommand budgetSet = new BudgetCommand(budget, "set");
    private Model model;
    private Model expectedModel;
    BudgetCommandTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void systemDateIsInCycleRange() {
        assert !budget.getCycleRange().equals(BudgetCommand.systemDateIsInCycleRange(budget));

    }

    @Test
    void saveBudget() {
        BudgetCommand.saveBudget(expectedModel, budget);
        Budget b = BudgetCommand.loadBudget(expectedModel);
        //assert b.getTotalBudget() == budget.getTotalBudget();
    }

    @Test
    void loadBudget_modelIsNotPresent() {
        //assert BudgetCommand.loadBudget(model).getTotalBudget() == Float.MAX_VALUE;
    }

    @Test
    void commandSetSuccess() {
        assertCommandSuccess(budgetSet, BudgetCommand.COMMAND_WORD, model,
                String.format(BudgetCommand.MESSAGE_SET, budget.getDurationAsString(), budget.getTotalBudget(),
                        budget.getRemainingBudget()), expectedModel);
    }

    @Test
    void commandViewSuccess() {
        String display = String.format(MESSAGE_VIEW,
                budget.getDurationAsString(), budget.getTotalBudget(), budget.getRemainingBudget(),
                budget.getRemainingDailyBudget(), budget.getDurationAsString());
        /* assertCommandSuccess(budgetView, BudgetCommand.COMMAND_WORD, model,
                display , expectedModel); */
    }
}
