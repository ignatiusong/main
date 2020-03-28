package seedu.foodiebot.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.foodiebot.model.budget.Budget;

/** A utility class containing a list of typical budgets. */
public class TypicalBudgets {

    public static final LocalDate LOCAL_DATE_A = LocalDate.of(2010, 12, 31);
    public static final LocalDate LOCAL_DATE_B = LocalDate.of(2018, 1, 20);
    public static final LocalDate LOCAL_DATE_C = LocalDate.of(2019, 2, 28);
    public static final LocalDate LOCAL_DATE_D = LocalDate.of(2020, 2, 29);
    public static final LocalDate LOCAL_DATE_E = LocalDate.of(2020, 3, 8);
    public static final LocalDate LOCAL_DATE_F = LocalDate.of(2020, 7, 31);
    public static final LocalDate LOCAL_DATE_G = LocalDate.of(2021, 3, 1);
    public static final LocalDate LOCAL_DATE_H = LocalDate.of(2022, 8, 18);

    public static final Budget BUDGET_A = new Budget(15, 15, "w/", LOCAL_DATE_A.atTime(LocalTime.now()));
    public static final Budget BUDGET_B = new Budget(15, 15, "w/", LOCAL_DATE_B.atTime(LocalTime.now()));
    public static final Budget BUDGET_C = new Budget(15, 15, "w/", LOCAL_DATE_C.atTime(LocalTime.now()));
    public static final Budget BUDGET_D = new Budget(15, 15, "w/", LOCAL_DATE_D.atTime(LocalTime.now()));
    public static final Budget BUDGET_E = new Budget(15, 15, "w/", LOCAL_DATE_E.atTime(LocalTime.now()));
    public static final Budget BUDGET_F = new Budget(15, 15, "w/", LOCAL_DATE_F.atTime(LocalTime.now()));
    public static final Budget BUDGET_G = new Budget(15, 15, "w/", LOCAL_DATE_G.atTime(LocalTime.now()));
    public static final Budget BUDGET_H = new Budget(15, 15, "w/", LOCAL_DATE_H.atTime(LocalTime.now()));

}
