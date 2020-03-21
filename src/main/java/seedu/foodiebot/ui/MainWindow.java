package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.logic.Logic;
/**
 * The Main Window. Provides the basic application layout containing a menu bar and space where
 * other JavaFX elements can be placed.
 */
public class MainWindow extends NoResultDisplayWindow {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());


    // Independent Ui parts residing in this Ui container
    private CanteenListPanel canteenListPanel;
    private ResultDisplay resultDisplay;
    @FXML
    private StackPane resultDisplayPlaceholder;

    private Logic logic;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(primaryStage, logic, FXML);
        this.logic = logic;
        primaryStage.getScene().setRoot(loadFxmlFile("MainScene.fxml"));
        new MainScene(primaryStage, logic, helpMenuItem);
    }
}
