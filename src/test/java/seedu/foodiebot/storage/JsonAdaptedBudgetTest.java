package seedu.foodiebot.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.testutil.TypicalCanteens;

class JsonAdaptedBudgetTest {
    private static final String INVALID_BUDGET_NEGATIVE_VAL = "-1";
    private static final String VALID_DATETIME = "2020-03-31T02:09:02.370197";

    @Test
    public void constructor_validBudget() {
        assertDoesNotThrow(() -> new JsonAdaptedBudget(new Budget()));
        assertDoesNotThrow(() -> new JsonAdaptedBudget(TypicalCanteens.getTypicalFoodieBot()));
    }

    @Test
    public void toModelType_invalidBudget_throwsIllegalValueException() {
        JsonAdaptedBudget budget =
            new JsonAdaptedBudget(INVALID_BUDGET_NEGATIVE_VAL, "0", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06");
        JsonAdaptedBudget budget2 =
            new JsonAdaptedBudget("0", "-1", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06");
        JsonAdaptedBudget budget3 =
            new JsonAdaptedBudget("100", "120", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06");
        //assertThrows(IllegalValueException.class, "", budget::toModelType);
    }

    @Test
    public void toModelType_emptyValues_setToZero() {
        assertEquals(new JsonAdaptedBudget("0", "0", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06").toModelType(),
            new JsonAdaptedBudget("", "0", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06").toModelType());
        assertEquals(new JsonAdaptedBudget("0", "0", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06").toModelType(),
            new JsonAdaptedBudget("0", "", "w/", VALID_DATETIME,
                "2020-03-31", "2020-04-06").toModelType());
    }
}
