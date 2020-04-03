package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;

class ReviewCommandTest {

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
    void execute_review_success() {
        //assertCommandSuccess(new ReviewCommand(Index.fromOneBased(1), new Review("Short review")),
        //    ReviewCommand.COMMAND_WORD, model,
        //    String.format(MESSAGE_SUCCESS, food.getName(), food.getReview().toString()), expectedModel);
    }
}
