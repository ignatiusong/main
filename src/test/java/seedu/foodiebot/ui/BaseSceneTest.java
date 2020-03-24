package seedu.foodiebot.ui;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class BaseSceneTest {
    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /**
     * Create the ui card.
     */
    @Start
    private void start(Stage stage) {
        //stage.getScene().setRoot(getRootNode());
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
        //FxAssert.verifyThat("#statusBarPlaceHolder", NodeMatchers.isNotNull());
    }

    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            return parent;
        } catch (IOException ex) {
            //Todo
        }
        return parent;
    }
}
