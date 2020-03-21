package seedu.foodiebot.ui;

import javafx.stage.Stage;

import seedu.foodiebot.logic.Logic;
import seedu.foodiebot.logic.commands.DirectionsCommandResult;

/** DirectionsScene class for creating a javafx scene. */
public class DirectionsScene extends BaseScene {
    public static final String FXML_FILE_FOLDER = "/view/";
    private Logic logic;
    private Stage primaryStage;

    public DirectionsScene(Stage primaryStage, Logic logic, DirectionsCommandResult commandResult) {
        super(primaryStage, logic);
        this.primaryStage = primaryStage;
        this.logic = logic;
        handleGoToCanteen(commandResult);
    }

    /**
     * Fills the directionsToCanteen region.
     */
    @javafx.fxml.FXML
    public void handleGoToCanteen(DirectionsCommandResult commandResult) {
        fillInnerParts();
        DirectionsToCanteenPanel directionsToCanteenPanel = new DirectionsToCanteenPanel();
        addToListPanel(directionsToCanteenPanel);
        directionsToCanteenPanel.fillView(commandResult.canteen);

    }
}
