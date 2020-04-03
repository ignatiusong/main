package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;

@ExtendWith(ApplicationExtension.class)
class TransactionsCardTest {
    private PurchasedFood food = new PurchasedFood(SampleDataUtil.getSampleFoods()[0], LocalDate.now(),
        LocalTime.now(), new Rating(10), new Review("This is a short review"));
    private TransactionsCard tCard = new TransactionsCard(food, 1);

    /**
     * Create the ui card.
     */
    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    public void equals() {
        TransactionsCard copy = new TransactionsCard(food, 1);
        assertEquals(tCard, copy);

        // same object -> returns true
        assertEquals(tCard, tCard);

        // null -> returns false
        assertNotEquals(null, tCard);
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(tCard.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#id", LabeledMatchers.hasText("1. "));
        FxAssert.verifyThat("#name", LabeledMatchers.hasText(food.getName()));
        FxAssert.verifyThat("#stallName",
            LabeledMatchers.hasText(food.getStallName()));
        FxAssert.verifyThat("#description",
            LabeledMatchers.hasText(food.getDescription()));
        FxAssert.verifyThat("#datePurchased",
            LabeledMatchers.hasText("Purchased: " + food.getDateAdded() + " at " + food.getTimeAdded()));
        FxAssert.verifyThat("#rating",
            LabeledMatchers.hasText("Rating: " + food.getRating().toString()));
        FxAssert.verifyThat("#review",
            LabeledMatchers.hasText("Review: " + food.getReview().toString()));
    }
}
