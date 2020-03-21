package seedu.foodiebot.ui;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import seedu.foodiebot.logic.Logic;

/** Main Scene without calling a new window. */
public class MainScene extends BaseScene {

    private CanteenListPanel canteenListPanel;
    public MainScene(Stage primaryStage, Logic logic, MenuItem helpMenuItem) {
        super(primaryStage, logic);
        fillInnerParts();
        this.helpMenuItem = helpMenuItem;
    }

    public MainScene(Stage primaryStage, Logic logic) {
        super(primaryStage, logic);
        fillInnerParts();
    }

    /** . */
    void fillInnerParts() {
        super.fillInnerParts();
        canteenListPanel = new CanteenListPanel(logic.getFilteredCanteenList(), false);
        addToListPanel(canteenListPanel);
        getResultDisplayPlaceholder().getChildren().add(getResultDisplay().getRoot());
    }
}
