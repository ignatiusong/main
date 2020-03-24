package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodiebot.testutil.TypicalStalls.MUSLIM;
import static seedu.foodiebot.testutil.TypicalStalls.TAIWANESE;

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
class StallCardTest {
    private StallCard stallCardTaiwanese =
            new StallCard(TAIWANESE, 1);

    private StallCard stallCardMuslim =
            new StallCard(MUSLIM, 1);

    @Test
    public void equals() {
        StallCard copy =
                new StallCard(TAIWANESE, 1);
        assertEquals(stallCardTaiwanese, copy);

        // same object -> returns true
        assertEquals(stallCardTaiwanese, stallCardTaiwanese);
        assertNotEquals(stallCardTaiwanese, stallCardMuslim);

        // null -> returns false
        assertNotEquals(null, stallCardTaiwanese);

        // different types -> returns false
        assertNotEquals(0, stallCardTaiwanese);

        // different stall, same index -> returns false
        assertNotEquals(stallCardTaiwanese, new StallCard(MUSLIM, 1));

        // same stall, different index -> returns false
        assertNotEquals(stallCardTaiwanese, new StallCard(TAIWANESE, 2));
    }


    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /** Create the ui card. */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(stallCardTaiwanese.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#id", LabeledMatchers.hasText("1. "));
        FxAssert.verifyThat("#name", LabeledMatchers.hasText(
            TAIWANESE.getName().fullName));
        FxAssert.verifyThat("#stallNumber", LabeledMatchers.hasText(
                String.valueOf(TAIWANESE.getStallNumber())));
        FxAssert.verifyThat("#cuisine", LabeledMatchers.hasText(
                String.valueOf(TAIWANESE.getCuisine())));
        FxAssert.verifyThat("#overallPriceRating", LabeledMatchers.hasText(
                String.valueOf(TAIWANESE.getOverallPriceRating())));
    }
}
