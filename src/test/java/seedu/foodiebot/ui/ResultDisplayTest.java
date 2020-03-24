package seedu.foodiebot.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class ResultDisplayTest {
    private ResultDisplay display = new ResultDisplay();
    private TextArea result;

    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(display.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void result_isValidOutput(FxRobot robot) {
        Assertions.assertThat(robot.lookup("#resultDisplay").queryAs(TextArea.class))
                .hasText("");
        robot.interact(() -> display.setFeedbackToUser("Result"));
        Assertions.assertThat(robot.lookup("#resultDisplay").queryAs(TextArea.class))
                .hasText("Result");
    }
}
