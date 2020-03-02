package seedu.foodiebot.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.commons.util.FileUtil;
import seedu.foodiebot.commons.util.JsonUtil;
import seedu.foodiebot.model.ReadOnlyFoodieBot;

/** A class to access AddressBook data stored as a json file on the hard disk. */
public class JsonFoodieBotStorage implements FoodieBotStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFoodieBotStorage.class);

    private Path filePath;

    public JsonFoodieBotStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot() throws DataConversionException {
        return readFoodieBot(filePath);
    }

    /**
     * Similar to {@link #readFoodieBot()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableAddressBook> jsonAddressBook =
                JsonUtil.readJsonFile(filePath, JsonSerializableAddressBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyFoodieBot addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyFoodieBot)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyFoodieBot addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAddressBook(addressBook), filePath);
    }
}
