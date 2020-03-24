package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.application.Platform;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class HelpWindowTest {
    private HelpWindow helpWindow;

    /**
     * Create the ui card.
     */
    @BeforeEach
    public void setupSpec(FxRobot robot) throws Exception {
        robot.interact(() -> helpWindow = new HelpWindow());
        FxToolkit.registerStage(helpWindow::getRoot);
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) throws TimeoutException {
        FxToolkit.showStage();
    }


    @Test
    public void isShowing_helpWindowIsShowing_returnsTrue(FxRobot robot) {
        Platform.runLater(() -> {
            robot.interact(() -> helpWindow.show());
            assertTrue(helpWindow.isShowing());
        });
    }

    @Test
    public void isShowing_helpWindowIsHiding_returnsFalse() {
        assertFalse(helpWindow.isShowing());
    }
}
