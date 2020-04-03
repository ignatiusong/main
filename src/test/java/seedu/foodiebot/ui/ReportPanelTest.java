package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.foodiebot.model.report.Report;

@ExtendWith(ApplicationExtension.class)
class ReportPanelTest {

    private ReportPanel panel = new ReportPanel();
    private ReportPanel copy = new ReportPanel();
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
        Platform.runLater(() -> panel.fillView(new Report()));
        Platform.runLater(() -> copy.fillView(new Report()));
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat("#textArea", LabeledMatchers.hasText(new Report().toString()));
    }
}
