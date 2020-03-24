package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;
import static seedu.foodiebot.testutil.TypicalCanteens.NUSFLAVORS;

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

@ExtendWith(ApplicationExtension.class)
class CanteenCardTest {
    private CanteenCard canteenCardDeck =
        new CanteenCard(DECK, 1, false);
    private CanteenCard canteenCardNusFlavors =
        new CanteenCard(NUSFLAVORS, 1, false);

    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    public void equals() {
        CanteenCard copy = new CanteenCard(DECK, 1, false);
        assertEquals(canteenCardDeck, copy);

        // same object -> returns true
        assertEquals(canteenCardDeck, canteenCardDeck);
        assertNotEquals(canteenCardDeck, canteenCardNusFlavors);

        // null -> returns false
        assertNotEquals(null, canteenCardDeck);

        // different types -> returns false
        assertNotEquals(0, canteenCardDeck);

        // different canteen, same index -> returns false
        assertNotEquals(canteenCardDeck, new CanteenCard(NUSFLAVORS, 1, false));

        // same canteen, different index -> returns false
        assertNotEquals(canteenCardDeck, new CanteenCard(NUSFLAVORS, 2, false));
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(canteenCardDeck.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#id", LabeledMatchers.hasText("1. "));
        FxAssert.verifyThat("#distance",
            LabeledMatchers.hasText(String.valueOf(DECK.getDistance())));
    }
}
