package seedu.foodiebot.ui;

import javafx.stage.Stage;

import seedu.foodiebot.logic.Logic;
import seedu.foodiebot.logic.commands.CommandResult;

/** Main Scene without calling a new window. */
public class MainScene extends BaseScene {

    private CanteenListPanel canteenListPanel;
    public MainScene(Stage primaryStage, Logic logic, CommandResult commandResult) {
        super(primaryStage, logic, commandResult);
        fillInnerParts();
    }

    void fillInnerParts() {
        super.fillInnerParts();
        canteenListPanel = new CanteenListPanel(logic.getFilteredCanteenList(), false);
        addToListPanel(canteenListPanel);
    }
}
