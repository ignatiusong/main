package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;

class ReportCommandTest {

    private Model model;
    private Model expectedModel;
    private LocalDate startDate = LocalDate.of(2020, 1, 20);
    private LocalDate sameEndDate = LocalDate.of(2020, 1, 20);
    private LocalDate endDate = LocalDate.of(2020, 2, 20);
    private DateRange dr = DateRange.of
            (startDate, endDate);
    ReportCommandTest() throws ParseException {
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
        assertCommandSuccess(new ReportCommand(dr),
              ReportCommand.COMMAND_WORD, model,
                String.format(ReportCommand.MESSAGE_SUCCESS, startDate, endDate), model);
        assertCommandSuccess(new ReportCommand(dr),
            ReportCommand.COMMAND_WORD, model,
            String.format(ReportCommand.MESSAGE_SUCCESS, startDate, sameEndDate), model);
        assertThrows(ParseException.class, () -> new ReportCommand(DateRange.of
            (endDate, startDate)));
    }

    @Test
    void needToSaveCommand() {
        assert(!new ReportCommand(dr).needToSaveCommand());
    }
}
