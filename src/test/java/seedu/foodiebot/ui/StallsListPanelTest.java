package seedu.foodiebot.ui;

import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ListViewMatchers;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.util.SampleDataUtil;

@ExtendWith(ApplicationExtension.class)
class StallsListPanelTest {
    private static final Stall CHINESE_COOKED_FOOD = new Stall(
        new Name("Chinese Cooked Food"), "The Deck", 1, "ChineseCookedFood.png",
        "asian",
        "$", 0, getTagSet("rice", "noodle", "cheap"), new ArrayList<>());

    private StallsListPanel panel = new StallsListPanel(
        FXCollections.observableArrayList(SampleDataUtil.getSampleStalls()));
    private ListView<Stall> listView;
    /** Create the ui card. */
    @BeforeAll
    public static void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /** Create the ui card. */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(panel.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();
        listView = panel.getSimpleListView();
    }

    @Test
    void init(FxRobot robot) {
        FxAssert.verifyThat(listView, ListViewMatchers.hasListCell(CHINESE_COOKED_FOOD));
        FxAssert.verifyThat(listView, ListViewMatchers.hasItems(
            SampleDataUtil.getSampleStalls().length));
    }
}
