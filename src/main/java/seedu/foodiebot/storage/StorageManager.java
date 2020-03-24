package seedu.foodiebot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.ReadOnlyUserPrefs;
import seedu.foodiebot.model.UserPrefs;

/** Manages storage of AddressBook data in local storage. */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FoodieBotStorage foodieBotStorage;
    private UserPrefsStorage userPrefsStorage;

    public StorageManager(FoodieBotStorage foodieBotStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.foodieBotStorage = foodieBotStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public StorageManager(FoodieBotStorage foodieBotStorage) {
        super();
        this.foodieBotStorage = foodieBotStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    //@Override
    //public Path getFoodieBotFilePath() {
    //    TODO Not Implemented
    //    return null;
    //}

    // ================ AddressBook methods ==============================
    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getCanteensFilePath() {
        return foodieBotStorage.getCanteensFilePath();
    }

    @Override
    public Path getStallsFilePath() {
        return foodieBotStorage.getStallsFilePath();
    }

    @Override
    public Path getFavoritesFilePath() {
        return foodieBotStorage.getFavoritesFilePath();
    }

    @Override
    public Path getFoodFilePath() {
        return foodieBotStorage.getFoodFilePath();
    }

    @Override
    public Path getTransactionsFilePath() {
        return foodieBotStorage.getTransactionsFilePath();
    }

    @Override
    public Path getBudgetFilePath() {
        return foodieBotStorage.getBudgetFilePath();
    }


    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot(String modelType) throws DataConversionException, IOException {
        return readFoodieBot(foodieBotStorage.getModelFilePath(modelType), modelType);
    }

    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath, String modelType)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodieBotStorage.readFoodieBot(modelType);
    }

    @Override
    public Path getModelFilePath(String modelType) {
        return foodieBotStorage.getModelFilePath(modelType);
    }

    @Override
    public void saveFoodieBot(ReadOnlyFoodieBot foodieBot) throws IOException {
        foodieBotStorage.saveFoodieBot(foodieBot, foodieBotStorage.getCanteensFilePath(), "Canteen");
    }

    @Override
    public void saveFoodieBot(ReadOnlyFoodieBot foodieBot, String modelType) throws IOException {
        foodieBotStorage.saveFoodieBot(foodieBot, modelType);
    }

    @Override
    public void saveFoodieBot(ReadOnlyFoodieBot foodieBot, Path filePath, String modelType) throws IOException {
        foodieBotStorage.saveFoodieBot(foodieBot, filePath, modelType);
    }


}
