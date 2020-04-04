package seedu.foodiebot.ui;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import seedu.foodiebot.MainApp;
import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.logic.Logic;

import seedu.foodiebot.logic.commands.ActionCommandResult;
import seedu.foodiebot.logic.commands.BackCommand;
import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.logic.commands.CommandResult;
import seedu.foodiebot.logic.commands.DirectionsCommandResult;
import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.RandomizeCommand;
import seedu.foodiebot.logic.commands.RateCommand;
import seedu.foodiebot.logic.commands.ReportCommand;
import seedu.foodiebot.logic.commands.ReviewCommand;
import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;

/**
 * Base class for creating a javafx scene.
 */
public class BaseScene {
    public static final String FXML_FILE_FOLDER = "/view/";
    @FXML
    protected MenuItem helpMenuItem;

    protected Logic logic;
    protected Stage primaryStage;
    // Independent Ui parts residing in this Ui container
    private final Logger logger = LogsCenter.getLogger(getClass());
    @FXML
    private StackPane statusbarPlaceholder;
    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane listPanelPlaceholder;

    private ResultDisplay resultDisplay;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private VBox vbox;
    @FXML
    private VBox stallList;

    @FXML
    private Label topLabel;

    @FXML
    private Label bottomLabel;

    private HelpWindow helpWindow;



    public BaseScene(Stage primaryStage, Logic logic) {
        this.primaryStage = primaryStage;
        this.logic = logic;
        fillInnerParts();
        helpWindow = new HelpWindow();
        showPinnedItem(false);
    }

    private static URL getFxmlFileUrl(String fxmlFileName) {
        requireNonNull(fxmlFileName);
        String fxmlFileNameWithFolder = FXML_FILE_FOLDER + fxmlFileName;
        URL fxmlFileUrl = MainApp.class.getResource(fxmlFileNameWithFolder);
        return requireNonNull(fxmlFileUrl);
    }

    void addToListPanel(UiPart<Region> regionUiPart) {
        listPanelPlaceholder = (StackPane) primaryStage.getScene().lookup("#listPanelPlaceholder");
        listPanelPlaceholder.getChildren().clear();
        listPanelPlaceholder.getChildren().add(regionUiPart.getRoot());
    }

    /**
     * Clears the extraListPanel and adds the regionUiPart
     *
     * @param regionUiPart
     */
    void addToExtraListPanel(UiPart<Region> regionUiPart) {
        StackPane extraListPanelPlaceholder = (StackPane) primaryStage.getScene().lookup(
            "#extraListPanelPlaceholder");
        extraListPanelPlaceholder.getChildren().clear();
        extraListPanelPlaceholder.getChildren().add(regionUiPart.getRoot());
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder = (StackPane) primaryStage.getScene().lookup("#commandBoxPlaceholder");

        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getFoodieBotFilePath());
        statusbarPlaceholder = (StackPane) primaryStage.getScene().lookup("#statusbarPlaceholder");
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());
        commandBox.getCommandTextField().requestFocus();

        vbox = (VBox) primaryStage.getScene().lookup("#vbox");
        stallList = (VBox) primaryStage.getScene().lookup("#stallList");
    }

    void updateStatusFooter(String message) {
        statusbarPlaceholder = (StackPane) primaryStage.getScene().lookup("#statusbarPlaceholder");
        statusbarPlaceholder.getChildren().clear();
        statusbarPlaceholder.getChildren().add(new Label(message));
    }

    StackPane getResultDisplayPlaceholder() {
        resultDisplayPlaceholder = (StackPane) primaryStage.getScene().lookup("#resultDisplayPlaceholder");
        return resultDisplayPlaceholder;
    }

    ResultDisplay getResultDisplay() {
        resultDisplay = new ResultDisplay();
        return resultDisplay;
    }
    /**
     * .
     */
    void updateResultDisplay(String result) {
        resultDisplayPlaceholder = (StackPane) primaryStage.getScene().lookup("#resultDisplayPlaceholder");
        resultDisplay = new ResultDisplay();
        if (!result.isEmpty()) {
            getResultDisplay().setFeedbackToUser(result);
            resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
        }
    }

    void updateResultDisplayContextAware(String result) {
        if (!ParserContext.getCurrentContext().equals(ParserContext.DIRECTIONS_CONTEXT)) {
            updateResultDisplay(result);
        }
    }

    /** Fills the stallListPanelRegion */
    @FXML
    public void handleListStalls() {
        Canteen cureentCanteen = ParserContext.getCurrentCanteen().get();

        showPinnedItem(false);
        VBox stallList = (VBox) loadFxmlFile("PinnedItem.fxml");

        topLabel = (Label) primaryStage.getScene().lookup("#topLabel");
        topLabel.setText("List of Stalls in " + cureentCanteen.getName());

        vbox.getChildren().add(vbox.getChildren().size() - 1, stallList);
        addToListPanel(new StallsListPanel(logic.getFilteredStallList(true)));
        addToExtraListPanel(new CanteenListPanel(FXCollections.observableArrayList(
            ParserContext.getCurrentCanteen().get()), false));

        bottomLabel = (Label) primaryStage.getScene().lookup("#bottomLabel");
    }

    /**
     * Fills the foodListPanel region.
     */
    @FXML
    public void handleListFood() {
        Stall currentStall = ParserContext.getCurrentStall().get();
        topLabel.setText("List of Foods in " + currentStall.getName());
        bottomLabel = (Label) primaryStage.getScene().lookup("#bottomLabel");
        bottomLabel.setText("Current Stall");

        addToListPanel(new FoodListPanel(logic.getFilteredFoodList(true)));
        addToExtraListPanel(new StallsListPanel(FXCollections.observableArrayList(
            ParserContext.getCurrentStall().get())));
    }

    /**
     * Fills the foodListPanel region.
     */
    @FXML
    public void handleListFavorites() {
        changeScene("MainScene.fxml");
        new BaseScene(primaryStage, logic);
        StackPane extraListPanelPlaceholder = (StackPane) primaryStage.getScene().lookup(
            "#extraListPanelPlaceholder");
        if (extraListPanelPlaceholder != null) {
            extraListPanelPlaceholder.getChildren().clear();
        }

        topLabel = (Label) primaryStage.getScene().lookup("#topLabel");
        topLabel.setText("List of Favorites");

        addToListPanel(new FoodListPanel(logic.getFilteredFavoriteFoodList(false)));

    }

    /**
     * .
     */
    @FXML
    public void handleListCanteens(CommandResult commandResult) {
        changeScene("MainScene.fxml");
        new MainScene(primaryStage, logic);

        topLabel = (Label) primaryStage.getScene().lookup("#topLabel");
        topLabel.setText("List of Canteens in NUS");

        addToListPanel(new CanteenListPanel(
            commandResult.isLocationSpecified()
                ? logic.getFilteredCanteenListSortedByDistance()
                : logic.getFilteredCanteenList(), commandResult.isLocationSpecified()));
        showPinnedItem(false);
    }
    /** .*/
    void showPinnedItem(boolean value) {
        if (!value) {
            if (vbox != null) {
                vbox.getChildren().remove(stallList);
            }
        }
    }

    /**
     * Fills the foodListPanel region.
     */
    @FXML
    public void handleListTransactions() {
        // changeScene("MainScene.fxml");
        // new MainScene(primaryStage, logic);
        topLabel.setText("List of Transactions");
        addToListPanel(new TransactionsPanel(logic.getFilteredTransactionsList()));

    }

    /** Fills the randomize panel. */
    @FXML
    public void handleListRandomize() {
        changeScene("MainScene.fxml");
        new BaseScene(primaryStage, logic);
        topLabel = (Label) primaryStage.getScene().lookup("#topLabel");
        topLabel.setText("List of Randomize Options");
        addToListPanel(new RandomizeListPanel(logic.getFilteredRandomizeList()));
    }

    /**
     * The method passed from logic to UI.
     */
    protected CommandResult executeCommand(String commandText)
        throws CommandException, ParseException, IOException {
        CommandResult commandResult = null;
        topLabel = (Label) primaryStage.getScene().lookup("#topLabel");
        try {
            commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            switch (commandResult.commandName) {
            case ListCommand.COMMAND_WORD:
                handleListCanteens(commandResult);
                ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case GoToCanteenCommand
                    .COMMAND_WORD:
                if (commandResult instanceof DirectionsCommandResult && ParserContext.getCurrentContext()
                    .equals(ParserContext.MAIN_CONTEXT)) {
                    //new DirectionsWindowScene(getPrimaryStage(), logic, (DirectionsCommandResult) commandResult)
                    // .show();

                    VBox pane = (VBox) loadFxmlFile("NoResultDisplayScene.fxml");
                    /* This is a negative example to load ui
                    Scene scene = new Scene(pane); // optionally specify dimensions too
                    getPrimaryStage().setScene(scene);
                    */
                    ParserContext.setCurrentContext(ParserContext.DIRECTIONS_CONTEXT);
                    primaryStage.getScene().setRoot(pane);
                    new DirectionsScene(primaryStage, logic, (DirectionsCommandResult) commandResult);
                }
                break;
            case EnterCanteenCommand.COMMAND_WORD:
                if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)
                    && ParserContext.getCurrentCanteen().isPresent()) {
                    ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
                    handleListStalls();
                } else if (ParserContext.getCurrentContext().equals(ParserContext.CANTEEN_CONTEXT)
                    && ParserContext.getCurrentStall().isPresent()) {
                    ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
                    handleListFood();
                } else if (ParserContext.getCurrentContext().equals(ParserContext.RANDOMIZE_CONTEXT)
                        && ParserContext.getCurrentStall().isPresent()) {
                    ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
                    handleListFood();
                }
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case FavoritesCommand.COMMAND_WORD:
                assert commandResult instanceof ActionCommandResult;
                switch (((ActionCommandResult) commandResult).action) {
                case "view":
                    handleListFavorites();
                    break;
                case "set":
                    break;
                case "remove":
                    handleListFavorites();
                    break;
                default:
                    break;
                }
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case TransactionsCommand.COMMAND_WORD:
                handleListTransactions();
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case ReportCommand.COMMAND_WORD:
                changeScene("MainScene.fxml");
                // VBox pane = (VBox) loadFxmlFile("NoResultDisplayScene.fxml");
                // primaryStage.getScene().setRoot(pane);
                new ReportScene(primaryStage, logic);
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case BudgetCommand.COMMAND_WORD:
                handleListTransactions();
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case RateCommand.COMMAND_WORD:
                handleListTransactions();
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case ReviewCommand.COMMAND_WORD:
                handleListTransactions();
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case RandomizeCommand.COMMAND_WORD:
                handleListRandomize();
                updateResultDisplay(commandResult.getFeedbackToUser());
                break;
            case BackCommand.COMMAND_WORD:
                topLabel.setText("");
                switch (ParserContext.getCurrentContext()) {
                case ParserContext.MAIN_CONTEXT:
                    handleListCanteens(commandResult);
                    break;
                case ParserContext.CANTEEN_CONTEXT:
                    handleListStalls();
                    break;
                case ParserContext.RANDOMIZE_CONTEXT:
                    handleListRandomize();
                    break;
                case ParserContext.DIRECTIONS_CONTEXT:
                    handleListCanteens(commandResult);
                    break;
                case ParserContext.FAVORITE_CONTEXT:
                    switch (ParserContext.getPreviousContext()) {
                    case ParserContext.STALL_CONTEXT:
                        handleListStalls();
                        ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
                        break;
                    default:
                        handleListCanteens(commandResult);
                        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
                        break;
                    }
                    break;
                case ParserContext.STALL_CONTEXT:
                    handleListFood();
                    break;
                default:
                    break;
                }
                updateResultDisplayContextAware(commandResult.getFeedbackToUser());
                break;
            default:
                updateResultDisplay(commandResult.getFeedbackToUser());
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
            updateResultDisplayContextAware(e.getMessage());
            throw e;
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    protected void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }
    /**
     * Switches the scene of the stage by setting the root.
     */
    protected void changeScene(String layoutName) {
        primaryStage.getScene().setRoot(loadFxmlFile(layoutName));
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    protected void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    /**
     * .
     */
    protected Parent loadFxmlFile(String fxmlFileName) {
        FXMLLoader newLoader = new FXMLLoader();
        newLoader.setLocation(getFxmlFileUrl(fxmlFileName));
        newLoader.setController(this);
        try {
            return newLoader.load();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
