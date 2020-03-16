package seedu.foodiebot.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.foodiebot.commons.core.GuiSettings;
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
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CanteenListPanel canteenListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private DirectionsToCanteenPanel directionsToCanteenPanel;
    private boolean isStallInitialised;
    private boolean isFoodInitialised;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane listPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();

        isStallInitialised = false;

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot()
            .addEventFilter(
                KeyEvent.KEY_PRESSED,
                event -> {
                    if (event.getTarget() instanceof TextInputControl
                        && keyCombination.match(event)) {
                        menuItem.getOnAction().handle(new ActionEvent());
                        event.consume();
                    }
                });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        canteenListPanel = new CanteenListPanel(logic.getFilteredCanteenList(), false);
        listPanelPlaceholder.getChildren().add(canteenListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFoodieBotFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Fills the directionsToCanteen region.
     */
    @FXML
    public void handleGoToCanteen() {
        directionsToCanteenPanel = new DirectionsToCanteenPanel();
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(directionsToCanteenPanel.getRoot());
    }

    /**
     * Fills the canteenListPanel region.
     */
    @FXML
    public void handleListCanteens(boolean isLocationSpecified) {
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(new CanteenListPanel(
            isLocationSpecified
                ? logic.getFilteredCanteenListSortedByDistance()
                : logic.getFilteredCanteenList(), isLocationSpecified).getRoot());
    }

    /**
     * Fills the stallListPanel region.
     */
    @FXML
    public void handleListStalls() {
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(new StallsListPanel(logic.getFilteredStallList(isStallInitialised))
                .getRoot());
        isStallInitialised = true;
    }

    /**
     * Fills the foodListPanel region.
     */
    @FXML
    public void handleListFood() {
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(new FoodListPanel(logic.getFilteredFoodList(isFoodInitialised))
                .getRoot());
        isFoodInitialised = true;
    }

    /**
     * Fills the foodListPanel region.
     */
    @FXML
    public void handleListFavorites() {
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(new FoodListPanel(logic.getFilteredFavoriteFoodList(false))
                .getRoot());
    }



    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings =
            new GuiSettings(
                primaryStage.getWidth(),
                primaryStage.getHeight(),
                (int) primaryStage.getX(),
                (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public CanteenListPanel getCanteenListPanel() {
        return canteenListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.foodiebot.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText)
            throws CommandException, ParseException, IOException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult instanceof DirectionsCommandResult) {
                handleGoToCanteen();
                directionsToCanteenPanel.fillView(((DirectionsCommandResult) commandResult).canteen);
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
                if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
                    handleListCanteens(commandResult.isLocationSpecified());
                } else if (ParserContext.getCurrentContext().equals(ParserContext.CANTEEN_CONTEXT)) {
                    handleListStalls();
                } else if (ParserContext.getCurrentContext().equals(ParserContext.STALL_CONTEXT)) {
                    handleListFood();
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
            throw e;
        }
    }
}
