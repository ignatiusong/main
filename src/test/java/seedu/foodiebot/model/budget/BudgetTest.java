package seedu.foodiebot.model.budget;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.util.SampleDataUtil;

class BudgetTest {
    private LocalDateTime dateTime = LocalDateTime.now();
    private Budget budget = new Budget(1.0f,
        0.5f, "d/", dateTime);

    @Test
    public void constructor_isValid() {
        assertDoesNotThrow(() -> budget);
    }

    @Test
    public void reset_clearsBudget() {
        Budget budget = new Budget(1.0f,
            0.5f, "d/", LocalDateTime.now());
        budget.resetRemainingBudget();
        assertEquals(budget.getRemainingBudget(), 1.0f);
    }

    @Test
    public void changeBudget_invalidAmount_returnsBudget() {
        Budget budget = new Budget(15f,
            0.5f, "d/", LocalDateTime.now());
        budget.subtractFromRemainingBudget(16f);
        assertEquals(budget.getRemainingBudget(), -15.5f);
        budget.addToRemainingBudget(15.5f);
        assertEquals(budget.getRemainingBudget(), 0f);
    }

    @Test
    public void are_stalls_equal() {
        Budget copy = new Budget(1.0f,
            0.5f, "d/", dateTime);

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(budget.hashCode(), copy.hashCode());

        assertFalse(budget.equals(canteen));
        assertTrue(budget.equals(budget));
    }
}
