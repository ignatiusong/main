package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class DirectionsToCanteenPanelTest {

    private DirectionsToCanteenPanel panel = new DirectionsToCanteenPanel();
    private DirectionsToCanteenPanel copy = new DirectionsToCanteenPanel();
    /**
     * Create the ui card.
     */
    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    public void equals() {
        assertEquals(panel, copy);

        // same object -> returns true
        assertEquals(panel, panel);

        // null -> returns false
        assertNotEquals(null, panel);
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(panel.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
        Platform.runLater(() -> panel.fillView(DECK));
        Platform.runLater(() -> copy.fillView(DECK));
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#textArea", LabeledMatchers.hasText(DECK.getDirectionsText()));
        FxAssert.verifyThat("#iv", NodeMatchers.isNotNull());
    }
}
