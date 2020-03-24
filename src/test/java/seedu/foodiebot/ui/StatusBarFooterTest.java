package seedu.foodiebot.ui;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class StatusBarFooterTest {
    private StatusBarFooter statusBarFooter;
    /* Create the ui card. */
    @BeforeEach
    public void setupSpec(FxRobot robot) throws Exception {
        robot.interact(() -> statusBarFooter = new StatusBarFooter(Path.of("/foodiebot.json")));
    }

    /** Create the ui card. */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(statusBarFooter.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }
}
