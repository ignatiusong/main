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
import seedu.foodiebot.model.UserPrefs;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonFoodieBotStorage implements FoodieBotStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFoodieBotStorage.class);

    private Path canteensFilePath;
    private Path stallsFilePath;
    private Path favoritesFilePath;
    private Path budgetFilePath;
    private Path foodFilePath;
    private Path transactionsFilePath;

    public JsonFoodieBotStorage(Path filePath) {
        this.canteensFilePath = filePath;
    }

    public JsonFoodieBotStorage(Path stallsFilePath, Path budgetFilePath, Path foodFilePath) {
        this.stallsFilePath = stallsFilePath;
        this.budgetFilePath = budgetFilePath;
        this.foodFilePath = foodFilePath;
    }

    public JsonFoodieBotStorage(Path canteensFilePath, Path stallsFilePath, Path budgetFilePath,
            Path foodFilePath, Path favoritesFilePath, Path transactionsFilePath) {
        this.canteensFilePath = canteensFilePath;
        this.stallsFilePath = stallsFilePath;
        this.budgetFilePath = budgetFilePath;
        this.foodFilePath = foodFilePath;
        this.favoritesFilePath = favoritesFilePath;
        this.transactionsFilePath = transactionsFilePath;
    }

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getCanteensFilePath() {
        return canteensFilePath;
    }

    @Override
    public Path getStallsFilePath() {
        return stallsFilePath;
    }

    @Override
    public Path getFoodFilePath() {
        return foodFilePath;
    }

    @Override
    public Path getFavoritesFilePath() {
        return favoritesFilePath;
    }

    @Override
    public Path getTransactionsFilePath() {
        return transactionsFilePath;
    }

    @Override
    public Path getBudgetFilePath() {
        return budgetFilePath;
    }


    @Override
    public Optional<ReadOnlyFoodieBot> readFoodieBot(String modelType) throws DataConversionException, IOException {
        return readFoodieBot(getModelFilePath(modelType), modelType);
    }

    /**
     * Similar to {@link #readFoodieBot(String modelType)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFoodieBot> readFoodieBot(Path filePath, String modelType) throws DataConversionException {
        requireNonNull(filePath);

        switch (modelType) {
        case "Canteen":
            Optional<JsonSerializableFoodieBot> jsonAddressBook = JsonUtil.readJsonFile(filePath,
                JsonSerializableFoodieBot.class);
            if (jsonAddressBook.isEmpty()) {
                return Optional.empty();
            }

            try {
                return Optional.of(jsonAddressBook.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }

        case "FavoriteFood":
            Optional<JsonSerializableFavorites> json1 = JsonUtil.readJsonFile(filePath,
                    JsonSerializableFavorites.class);
            if (json1.isEmpty()) {
                return Optional.empty();
            }

            try {
                return Optional.of(json1.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }

        case "Stall":
            Optional<JsonSerializableStall> json2 = JsonUtil.readJsonFile(filePath, JsonSerializableStall.class);
            if (json2.isEmpty()) {
                return Optional.empty();
            }

            try {
                return Optional.of(json2.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }

        case "Budget":
            Optional<JsonAdaptedBudget>jsonAdaptedBudget = JsonUtil.readJsonFile(filePath, JsonAdaptedBudget.class);
            if (jsonAdaptedBudget.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(jsonAdaptedBudget.get().toModelType());

        case "Food":
            Optional<JsonSerializableFood> json3 = JsonUtil.readJsonFile(filePath,
                    JsonSerializableFood.class);
            if (json3.isEmpty()) {
                return Optional.empty();
            }

            try {
                return Optional.of(json3.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }

        case "Transactions":
            Optional<JsonSerializableTransactions> jsonTransactions = JsonUtil.readJsonFile(filePath,
                    JsonSerializableTransactions.class);
            if (jsonTransactions.isEmpty()) {
                return Optional.empty();
            }

            try {
                return Optional.of(jsonTransactions.get().toModelType());
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }

        default:
            break;

        }
        return Optional.empty();
    }


    @Override
    public Path getModelFilePath(String modelType) {
        Path path = null;
        switch (modelType) {
        case "Canteen":
            return new UserPrefs().getFoodieBotFilePath();
        case "Stall":
            return new UserPrefs().getStallsFilePath();
        case "Budget":
            return new UserPrefs().getBudgetFilePath();
        case "Food":
            return new UserPrefs().getFoodFilePath();
        case "FavoriteFood":
            return new UserPrefs().getFavoriteFoodFilePath();
        case "Transactions":
            return new UserPrefs().getTransactionsFilePath();
        default:
            break;
        }
        return path;
    }

    /**
     * Saves the given {@link ReadOnlyFoodieBot} to the storage.
     *
     * @param foodieBot cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveFoodieBot(ReadOnlyFoodieBot foodieBot) throws IOException {
        saveFoodieBot(foodieBot, "Canteen");
    }

    /**
     * Save foodiebot according to the model supplied.
     **/
    @Override
    public void saveFoodieBot(ReadOnlyFoodieBot foodieBot, String modelType) throws IOException {
        saveFoodieBot(foodieBot, getModelFilePath(modelType), modelType);
    }

    /**
     * Similar to {@link #saveFoodieBot(ReadOnlyFoodieBot)}.
     *
     * TIP: When adding a new entity, a new {@code JsonSerialiablexxx} needs to be created
     * and filePath has to be specified in {@link JsonFoodieBotStorage}.
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveFoodieBot(
        ReadOnlyFoodieBot foodieBot, Path filePath,
        String modelType) throws IOException {

        requireNonNull(filePath);
        FileUtil.createIfMissing(filePath);
        switch (modelType) {
        case "Canteen":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableFoodieBot(foodieBot), filePath);
            break;
        case "Stall":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableStall(foodieBot), filePath);
            break;
        case "Budget":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonAdaptedBudget(foodieBot), filePath);
            break;
        case "Food":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableFood(foodieBot), filePath);
            break;
        case "FavoriteFood":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableFavorites(foodieBot), filePath);
            break;
        case "Transactions":
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableTransactions(foodieBot), filePath);
            break;
        default:
            requireNonNull(foodieBot);
            JsonUtil.saveJsonFile(new JsonSerializableFoodieBot(foodieBot), filePath);
            break;
        }


    }
}
