package seedu.foodiebot.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.logic.commands.ClearCommand;
import seedu.foodiebot.logic.commands.Command;
import seedu.foodiebot.logic.commands.CommandResult;
import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.commands.ExitCommand;
import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.commands.FindCommand;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.logic.commands.HelpCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.RandomizeCommand;
import seedu.foodiebot.logic.commands.RateCommand;
import seedu.foodiebot.logic.commands.ReportCommand;
import seedu.foodiebot.logic.commands.ReviewCommand;
import seedu.foodiebot.logic.commands.SelectItemCommand;
import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.FoodieBotParser;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.favorites.FavoriteFood;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.randomize.Randomize;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FoodieBotParser foodieBotParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        foodieBotParser = new FoodieBotParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = foodieBotParser.parseCommand(commandText);
        commandResult = command.execute(model);

        if (command.needToSaveCommand()) {
            try {
                storage.saveFoodieBot(model.getFoodieBot(),
                    mapCommandToModelName(commandResult.commandName));
            } catch (IOException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
        }
        return commandResult;
    }

    /**
     * Maps the command to the relevant model for storage
     */
    public String mapCommandToModelName(String commandName) {
        switch (commandName) {
        case GoToCanteenCommand.COMMAND_WORD:
        case ListCommand.COMMAND_WORD:
            return Canteen.class.getSimpleName();

        case EnterCanteenCommand.COMMAND_WORD:
            if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
                return Stall.class.getSimpleName();
            } else {
                return Food.class.getSimpleName();
            }

        case BudgetCommand.COMMAND_WORD:
            return Budget.class.getSimpleName();

        case ReportCommand.COMMAND_WORD:
            //TODO Not Implemented

        case RandomizeCommand.COMMAND_WORD:
            return Randomize.class.getSimpleName();

        case FavoritesCommand.COMMAND_WORD:
            return FavoriteFood.class.getSimpleName();

        case TransactionsCommand.COMMAND_WORD:
            return "Transactions";

        case ClearCommand.COMMAND_WORD:
            //TODO Not Implemented

        case RateCommand.COMMAND_WORD:
            return "Transactions";

        case ReviewCommand.COMMAND_WORD:
            return "Transactions";

        case FindCommand.COMMAND_WORD:
            //TODO Not Implemented

        case ExitCommand.COMMAND_WORD:
            //TODO Not Implemented

        case HelpCommand.COMMAND_WORD:
            //TODO Not Implemented
        case SelectItemCommand.COMMAND_WORD:
            return "Transactions";
        default:
            return "";
        }
    }

    /**
     * Returns the FoodieBot.
     *
     * @see Model#getFoodieBot()
     */
    @Override
    public ReadOnlyFoodieBot getFoodieBot() {
        return model.getFoodieBot();
    }

    @Override
    public ObservableList<Canteen> getFilteredCanteenList() {
        return model.getFilteredCanteenList();
    }

    /**
     * Returns an unmodifiable view of the filtered list of canteens sorted by distance
     */
    @Override
    public ObservableList<Canteen> getFilteredCanteenListSortedByDistance() {
        return model.getFilteredCanteenListSortedByDistance();
    }


    @Override
    public Path getFoodieBotFilePath() {
        return model.getFoodieBotFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<Stall> getFilteredStallList(boolean isInitialised) {
        return model.getFilteredStallList(isInitialised);
    }

    public ObservableList<Stall> getFilteredStallList() {
        return model.getFilteredStallList();
    }

    @Override
    public ObservableList<Food> getFilteredFoodList(boolean isInitialised) {
        return model.getFilteredFoodList(isInitialised);
    }

    @Override
    public ObservableList<Food> getFilteredFavoriteFoodList(boolean isInitialised) {
        return model.getFilteredFavoriteFoodList(isInitialised);
    }

    @Override
    public ObservableList<PurchasedFood> getFilteredTransactionsList() {
        return model.getFilteredTransactionsList();
    }

    @Override
    public ObservableList<Stall> getFilteredRandomizeList() {
        return model.getFilteredRandomizeList();
    }

}
