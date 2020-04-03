package seedu.foodiebot.ui;

import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.foodiebot.model.canteen.Canteen;

@ExtendWith(ApplicationExtension.class)
class RandomizeListPanelTest {
    private RandomizeListPanel panel = new RandomizeListPanel(getTypicalFoodieBot().getStallList());
    private ListView<Canteen> listView;

    /**
     * Create the ui card.
     */
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
        stackPane.getChildren().add(panel.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @Test
    void init(FxRobot robot) {
    }
}
