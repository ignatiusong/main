package seedu.foodiebot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;

/** Represents a storage for {@link FoodieBot}. */
public interface FoodieBotStorage {

    /** Returns the file path of the data file. */
    Path getCanteensFilePath();

    Path getStallsFilePath();

    Path getFavoritesFilePath();

    Path getFoodFilePath();

    Path getTransactionsFilePath();

    Path getBudgetFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFoodieBot}. Returns {@code Optional.empty()} if
     * storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodieBot> readFoodieBot(String modelType) throws DataConversionException, IOException;

    /** @see #getCanteensFilePath() */
    Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath, String modelType)
            throws DataConversionException, IOException;


    Path getModelFilePath(String modelType);


    /**
     * Saves the given {@link ReadOnlyFoodieBot} to the storage.
     *
     * @param foodieBot cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodieBot(ReadOnlyFoodieBot foodieBot) throws IOException;

    void saveFoodieBot(ReadOnlyFoodieBot foodieBot, String modelType) throws IOException;

    ///** @see #saveFoodieBot(ReadOnlyFoodieBot) */
    void saveFoodieBot(ReadOnlyFoodieBot foodieBot, Path filePath, String modelType)
            throws IOException;

}
