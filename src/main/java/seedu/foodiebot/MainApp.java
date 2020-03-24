package seedu.foodiebot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;

import seedu.foodiebot.commons.core.Config;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.Version;
import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.commons.util.ConfigUtil;
import seedu.foodiebot.commons.util.StringUtil;
import seedu.foodiebot.logic.Logic;
import seedu.foodiebot.logic.LogicManager;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.ReadOnlyUserPrefs;
import seedu.foodiebot.model.UserPrefs;
// import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.favorites.FavoriteFood;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.storage.FoodieBotStorage;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.JsonUserPrefsStorage;
import seedu.foodiebot.storage.Storage;
import seedu.foodiebot.storage.StorageManager;
import seedu.foodiebot.storage.UserPrefsStorage;
import seedu.foodiebot.ui.Ui;
import seedu.foodiebot.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info(
            "=============================[ Initializing FoodieBot ]===========================");
        super.init();

        try {
            AppParameters appParameters = AppParameters.parse(getParameters());
            config = initConfig(appParameters.getConfigPath());
        } catch (Exception ex) {
            config = new Config();
            logger.info("Parameters are empty");
        }


        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);

        // Loads the specified file paths into the main app.
        FoodieBotStorage foodieBotStorage =
            new JsonFoodieBotStorage(userPrefs.getFoodieBotFilePath(),
                    userPrefs.getStallsFilePath(), userPrefs.getFoodFilePath(),
                    userPrefs.getBudgetFilePath(), userPrefs.getFavoriteFoodFilePath(),
                    userPrefs.getTransactionsFilePath());

        storage = new StorageManager(foodieBotStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    protected static Logger getLogger() {
        return logger;
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s canteen data and {@code
     * userPrefs}. <br>
     * The data from the sample FoodieBot will be used instead if {@code storage}'s FoodieBot
     * is not found, or an empty FoodieBot will be used instead if errors occur when reading
     * {@code storage}'s FoodieBot.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        Optional<ReadOnlyFoodieBot> foodieBotOptional;
        ReadOnlyFoodieBot initialData;
        try {
            storage.readFoodieBot(Canteen.class.getSimpleName());

            // storage.readFoodieBot(Budget.class.getSimpleName());
            storage.readFoodieBot("Transactions");

            storage.readFoodieBot(Food.class.getSimpleName());
            storage.readFoodieBot(FavoriteFood.class.getSimpleName());

            foodieBotOptional = Optional.empty();
            logger.info("Data file not found. Will be starting with a sample FoodieBot");
            initialData = foodieBotOptional.orElseGet(SampleDataUtil::getSampleFoodieBot);
        } catch (DataConversionException e) {
            logger.warning(
                "Data file not in the correct format. Will be starting with an empty FoodieBot");
            initialData = new FoodieBot();
        } catch (IOException e) {
            logger.warning(
                "Problem while reading from the file. Will be starting with an empty FoodieBot");
            initialData = new FoodieBot();
        }

        return new ModelManager(initialData, userPrefs);
    }


    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead if {@code
     * configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning(
                "Config file at "
                    + configFilePathUsed
                    + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        // Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a
     * new {@code UserPrefs} with default configuration if errors occur when reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning(
                "UserPrefs file at "
                    + prefsFilePath
                    + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning(
                "Problem while reading from the file. Will be starting with an empty FoodieBot");
            initializedPrefs = new UserPrefs();
        }

        // Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting FoodieBot " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info(
            "============================ [ Stopping FoodieBot ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
