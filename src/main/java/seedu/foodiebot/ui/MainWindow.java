package seedu.foodiebot.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.logic.Logic;
import seedu.foodiebot.logic.commands.CommandResult;
import seedu.foodiebot.logic.commands.DirectionsCommandResult;
import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.commands.ExitCommand;
import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

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
        fillInnerParts();
    }

    public MainWindow(Stage primaryStage, Logic logic, String message) {
        super(primaryStage, logic, FXML);
        this.logic = logic;
        fillInnerParts();
        requireNonNull(resultDisplay);
        resultDisplay.setFeedbackToUser(message);
        resultDisplayPlaceholder.getChildren().clear();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    @Override
    void fillInnerParts() {
        super.fillInnerParts();
        canteenListPanel = new CanteenListPanel(logic.getFilteredCanteenList(), false);
        getListPanelPlaceholder().getChildren().add(canteenListPanel.getRoot());
        resultDisplay = new ResultDisplay();
    }
    /**
     * Fills the canteenListPanel region.
     */
    @FXML
    public void handleListCanteens(boolean isLocationSpecified) {
        getListPanelPlaceholder().getChildren().clear();
        getListPanelPlaceholder().getChildren().add(new CanteenListPanel(
            isLocationSpecified
                ? logic.getFilteredCanteenListSortedByDistance()
                : logic.getFilteredCanteenList(), isLocationSpecified).getRoot());
    }

    @Override
    protected CommandResult executeCommand(String commandText) throws CommandException, ParseException, IOException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult instanceof DirectionsCommandResult) {
                //new DirectionsWindowScene(getPrimaryStage(), logic, (DirectionsCommandResult) commandResult).show();

                VBox pane = (VBox) loadFxmlFile("NoResultDisplayScene.fxml");
                /* This is a negative example to load ui
                Scene scene = new Scene(pane); // optionally specify dimensions too
                getPrimaryStage().setScene(scene);
                */
                getPrimaryStage().getScene().setRoot(pane);
                new DirectionsScene(getPrimaryStage(), logic, (DirectionsCommandResult) commandResult);

            }

            switch (commandResult.commandName) {
            case ListCommand.COMMAND_WORD:
                if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
                    handleListCanteens(commandResult.isLocationSpecified());
                } else {
                    resultDisplay.setFeedbackToUser(ParserContext.INVALID_CONTEXT_MESSAGE);
                }
                break;
            case EnterCanteenCommand.COMMAND_WORD:
                if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
                    ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
                    handleListStalls();
                } else if (ParserContext.getCurrentContext().equals(ParserContext.CANTEEN_CONTEXT)) {
                    ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
                    handleListFood();
                }
                break;
            case FavoritesCommand.COMMAND_WORD:
                handleListFavorites();
                break;
            case ExitCommand.COMMAND_WORD:
                switch (ParserContext.getCurrentContext()) {
                case ParserContext.MAIN_CONTEXT:
                    handleListCanteens(commandResult.isLocationSpecified());
                    break;
                case ParserContext.CANTEEN_CONTEXT:
                    handleListStalls();
                    break;
                case ParserContext.STALL_CONTEXT:
                    handleListFood();
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }
            return commandResult;
        } catch (CommandException | ParseException | IOException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            resultDisplayPlaceholder.getChildren().clear();
            resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
            throw e;
        }

    }
}
