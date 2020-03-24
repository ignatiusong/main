package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;

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

import seedu.foodiebot.model.food.Food;

@ExtendWith(ApplicationExtension.class)
class FoodCardTest {
    private Food comboSet = new Food("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            1, "Nus Flavors", "Western", getTagSet("western", "expensive"));
    private FoodCard foodCard = new FoodCard(comboSet, 1);

    /**
     * Create the ui card.
     */
    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    public void equals() {
        FoodCard copy = new FoodCard(comboSet, 1);
        assertEquals(foodCard, copy);

        // same object -> returns true
        assertEquals(foodCard, foodCard);

        // null -> returns false
        assertNotEquals(null, foodCard);
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(foodCard.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#id", LabeledMatchers.hasText("1. "));
        FxAssert.verifyThat("#name", LabeledMatchers.hasText(comboSet.getName()));
        FxAssert.verifyThat("#stallName",
                LabeledMatchers.hasText(comboSet.getStallName()));
        FxAssert.verifyThat("#description",
                LabeledMatchers.hasText(comboSet.getDescription()));
    }
}
