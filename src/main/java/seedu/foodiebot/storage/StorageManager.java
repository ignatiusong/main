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

    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return foodieBotStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot() throws DataConversionException, IOException {
        logger.fine("test");
        return readFoodieBot(foodieBotStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodieBotStorage.readFoodieBot(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyFoodieBot addressBook) throws IOException {
        saveAddressBook(addressBook, foodieBotStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyFoodieBot addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodieBotStorage.saveAddressBook(addressBook, filePath);
    }
}
