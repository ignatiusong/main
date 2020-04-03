package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

class TransactionsCommandTest {

    private LocalDate startDate = LocalDate.of(2020, 1, 20);
    private LocalDate endDate = LocalDate.of(2020, 2, 20);
    private DateRange dr = DateRange.of
            (startDate, endDate);
    private Model model;
    private Model expectedModel;

    TransactionsCommandTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_success() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
        assertCommandSuccess(new TransactionsCommand(dr),
            TransactionsCommand.COMMAND_WORD, model,
                String.format(TransactionsCommand.MESSAGE_SUCCESS, startDate, endDate), model);
    }

    @Test
    void needToSaveCommand() {
        assert (!new TransactionsCommand(dr).needToSaveCommand());
    }
}
