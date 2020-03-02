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
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyFoodieBot}. Returns {@code Optional.empty()} if
     * storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodieBot> readFoodieBot() throws DataConversionException, IOException;

    /** @see #getAddressBookFilePath() */
    Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodieBot} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyFoodieBot addressBook) throws IOException;

    /** @see #saveAddressBook(ReadOnlyFoodieBot) */
    void saveAddressBook(ReadOnlyFoodieBot addressBook, Path filePath) throws IOException;
}
