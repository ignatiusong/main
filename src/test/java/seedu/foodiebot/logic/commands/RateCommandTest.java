package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;

class RateCommandTest {

    private Model model;
    private Model expectedModel;
    private PurchasedFood food = new PurchasedFood(SampleDataUtil.getSampleFoods()[0], LocalDate.now(),
        LocalTime.now(), new Rating(10), new Review("This is a short review"));


    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodieBot(), new UserPrefs());
        expectedModel = new ModelManager(model.getFoodieBot(), new UserPrefs());
    }

    @Test
    void execute_rate_success() {
        assertNotNull(model);
        RateCommand command = new RateCommand(Index.fromOneBased(1), new Rating(10));

        assertTrue(command.needToSaveCommand());

        //assertCommandSuccess(command, RateCommand.COMMAND_WORD, model,
        //    String.format(MESSAGE_SUCCESS, food.getName(), food.getRating().toString()), expectedModel);

        //null parameters
        assertThrows(NullPointerException.class, () -> new RateCommand(null, new Rating(10)));
        assertThrows(NullPointerException.class, () -> new RateCommand(Index.fromOneBased(1), null));

        //invalid index
        assertDoesNotThrow(() -> new RateCommand(Index.fromOneBased(100), new Rating(10)));
        assertThrows(IllegalArgumentException.class, () -> new RateCommand(Index.fromOneBased(1), new Rating(11)));
    }
}
