package seedu.foodiebot.model;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.storage.FoodieBotStorage;
import seedu.foodiebot.storage.JsonFoodieBotStorage;
import seedu.foodiebot.storage.Storage;
import seedu.foodiebot.storage.StorageManager;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodieBot foodieBot;
    private final UserPrefs userPrefs;
    private final FilteredList<Canteen> filteredCanteens;
    private final FilteredList<Stall> filteredStalls;
    private final FilteredList<Food> filteredFoods;

    private final Budget budget;
    private final FilteredList<Food> filteredFavoriteFoodList;
    private FilteredList<PurchasedFood> filteredTransactionsList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyFoodieBot foodieBot, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(foodieBot, userPrefs);

        logger.fine("Initializing with food data: " + foodieBot + " and user prefs " + userPrefs);

        this.foodieBot = new FoodieBot(foodieBot);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCanteens = new FilteredList<>(this.foodieBot.getCanteenList());
        filteredStalls = new FilteredList<>(this.foodieBot.getStallList());
        filteredFoods = new FilteredList<>(this.foodieBot.getFoodList());
        filteredFavoriteFoodList = new FilteredList<>(this.foodieBot.getFavoriteFoodList());
        filteredTransactionsList = new FilteredList<>(this.foodieBot.getTransactionsList());
        budget = this.foodieBot.getBudget();
    }

    public ModelManager() {
        this(new FoodieBot(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getFoodieBotFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setFoodieBotFilePath(addressBookFilePath);
    }

    // =========== AddressBook
    // ================================================================================

    @Override
    public ReadOnlyFoodieBot getFoodieBot() {
        return foodieBot;
    }

    @Override
    public void setFoodieBot(ReadOnlyFoodieBot foodieBot) {
        this.foodieBot.resetData(foodieBot);
    }

    @Override
    public boolean hasCanteen(Canteen canteen) {
        requireNonNull(canteen);
        return foodieBot.hasCanteen(canteen);
    }

    @Override
    public void deleteCanteen(Canteen target) {
        foodieBot.removeCanteen(target);
    }

    @Override
    public void addCanteen(Canteen canteen) {
        foodieBot.addCanteen(canteen);
        updateFilteredCanteenList(PREDICATE_SHOW_ALL);
    }

    @Override
    public void setCanteen(Canteen target, Canteen editedCanteen) {
        requireAllNonNull(target, editedCanteen);

        foodieBot.setCanteen(target, editedCanteen);
    }

    /**
     * Reads the stored budget in the Json file.
     *
     * @return The budget object stored in the Json file. If the file is not present,
     * returns an empty Optional value.
     */
    @Override
    public Optional<Budget> getBudget() {
        try {
            FoodieBotStorage foodieBotStorage =
                    new JsonFoodieBotStorage(userPrefs.getFoodieBotFilePath(), userPrefs.getStallsFilePath(),
                            userPrefs.getBudgetFilePath(), userPrefs.getFoodieBotFilePath(),
                            userPrefs.getFavoriteFoodFilePath(), userPrefs.getTransactionsFilePath());
            Storage storage = new StorageManager(foodieBotStorage);
            Optional<ReadOnlyFoodieBot> newBot = storage.readFoodieBot(Budget.class.getSimpleName());
            if (newBot.equals(Optional.empty())) {
                return Optional.empty();
            }

            Budget budget = newBot.get().getBudget();


            return Optional.of(budget);
        } catch (DataConversionException e) {
            return Optional.empty();
        } catch (IOException e) {
            return Optional.empty();
        }

    }

    @Override
    public void setBudget(Budget budget) {
        requireAllNonNull(budget);
        foodieBot.setBudget(budget);
    }

    /**
     * This function return a FileReader of the jsonfile (foodiebot.json).
     * @return FileReader of the jsonfile
     * @throws FileNotFoundException
     */
    @Override
    public FileReader listOfCanteens() throws FileNotFoundException {
        FoodieBotStorage foodieBotStorage =
                new JsonFoodieBotStorage(userPrefs.getFoodieBotFilePath());
        return new FileReader(String.valueOf(foodieBotStorage.getCanteensFilePath()));
    }

    /**
     * This function return a FileReader of the jsonfile (foodiebot-stalls.json).
     * @return FileReader of the jsonfile
     * @throws FileNotFoundException
     */
    @Override
    public FileReader listOfStalls() throws FileNotFoundException {
        FoodieBotStorage foodieBotStorage =
                new JsonFoodieBotStorage(userPrefs.getFoodieBotFilePath(), userPrefs.getStallsFilePath(),
                        userPrefs.getBudgetFilePath(), userPrefs.getFoodieBotFilePath(),
                        userPrefs.getFavoriteFoodFilePath(), userPrefs.getTransactionsFilePath());
        return new FileReader(String.valueOf(foodieBotStorage.getStallsFilePath()));
    }


    // =========== Filtered Canteen List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Canteen}
     */
    @Override
    public ObservableList<Canteen> getFilteredCanteenList() {
        SortedList<Canteen> sortedCanteenList = new SortedList<>(filteredCanteens);
        sortedCanteenList.setComparator(Comparator.comparing((Canteen c) -> c.getName().fullName));
        return sortedCanteenList;
    }

    /**
     * Get filtered canteen list sorted by distance
     */
    @Override
    public ObservableList<Canteen> getFilteredCanteenListSortedByDistance() {
        SortedList<Canteen> sortedCanteenList = new SortedList<>(filteredCanteens);
        sortedCanteenList.setComparator(Comparator.comparingInt(Canteen::getDistance));
        return sortedCanteenList;
    }

    @Override
    public void updateFilteredCanteenList(Predicate<Canteen> predicate) {
        requireNonNull(predicate);
        filteredCanteens.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Stall} backed by the internal list of
     * {@code versionedAddressBook}
     */
    public ObservableList<Stall> getFilteredStallList(boolean isInitialised) {
        if (!isInitialised) {
            updateModelManagerStalls();
        }

        return filteredStalls;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Stall} backed by the internal list of
     * {@code versionedAddressBook}
     */
    public ObservableList<Stall> getFilteredStallList() {
        return filteredStalls;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoods;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList(boolean isInitialised) {
        if (!isInitialised) {
            updateModelManagerFood();
        }
        return filteredFoods;
    }

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     *
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoods.setPredicate(predicate);
        //List<Food> filterCopy = new ArrayList<>(filteredFoods);
        //filteredFoods.removeAll(filterCopy);
        //filteredFoods.addAll(foods);
    }

    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     *
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredStallList(Predicate<Stall> predicate) {
        requireNonNull(predicate);
        filteredStalls.setPredicate(predicate);
    }

    @Override
    public boolean isLocationSpecified() {
        return foodieBot.isLocationSpecified();
    }

    @Override
    public void setLocationSpecified(boolean isLocationSpecified) {
        foodieBot.setLocationSpecified(isLocationSpecified);
    }

    @Override
    public void setFavorite(Food food) {
        requireAllNonNull(food);
        foodieBot.setFavorite(food);
    }

    @Override
    public ObservableList<Food> getFilteredFavoriteFoodList() {
        return filteredFavoriteFoodList;
    }

    @Override
    public void updateFilteredFavoriteList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFavoriteFoodList.setPredicate(predicate);
    }

    @Override
    public ObservableList<PurchasedFood> getFilteredTransactionsList() {
        return filteredTransactionsList;
    }

    @Override
    public void updateFilteredTransactionsList(Predicate<PurchasedFood> predicate) {
        requireNonNull(predicate);
        filteredTransactionsList.setPredicate(predicate);
    }

    @Override
    public void addPurchasedFood(PurchasedFood food) {
        requireNonNull(food);

        foodieBot.addPurchasedFood(food);

    }

    @Override
    public void loadFilteredTransactionsList() {
        try {
            FoodieBotStorage foodieBotStorage =
                    new JsonFoodieBotStorage(userPrefs.getFoodieBotFilePath(), userPrefs.getStallsFilePath(),
                            userPrefs.getBudgetFilePath(), userPrefs.getFoodieBotFilePath(),
                            userPrefs.getFavoriteFoodFilePath(), userPrefs.getTransactionsFilePath());
            Storage storage = new StorageManager(foodieBotStorage);
            Optional<ReadOnlyFoodieBot> newBot = storage.readFoodieBot("Transactions");

            if (!newBot.equals(Optional.empty())) {
                this.foodieBot.setTransactionsList(newBot.get().getTransactionsList());
                filteredTransactionsList = new FilteredList<PurchasedFood>(newBot.get().getTransactionsList());
            }

        } catch (DataConversionException e) {
            // return Optional.empty();
        } catch (IOException e) {
            // return Optional.empty();
        }
    }

    /**
     * .
     */
    public void updateModelManagerStalls() {
        try {
            FoodieBotStorage foodieBotStorage =
                    new JsonFoodieBotStorage(userPrefs.getStallsFilePath());
            Storage storage = new StorageManager(foodieBotStorage);
            Optional<ReadOnlyFoodieBot> newBot = storage.readFoodieBot(Stall.class.getSimpleName());
            foodieBot.setStalls(newBot.get().getStallList());
        } catch (DataConversionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * .
     */
    public void updateModelManagerFood() {
        try {
            FoodieBotStorage foodieBotStorage =
                    new JsonFoodieBotStorage(
                            userPrefs.getStallsFilePath(), userPrefs.getBudgetFilePath(), userPrefs.getFoodFilePath());
            Storage storage = new StorageManager(foodieBotStorage);
            Optional<ReadOnlyFoodieBot> newBot = storage.readFoodieBot(Food.class.getSimpleName());
            foodieBot.setFood(newBot.get().getFoodList());
        } catch (DataConversionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return foodieBot.equals(other.foodieBot)
                && userPrefs.equals(other.userPrefs)
                && filteredCanteens.equals(other.filteredCanteens);
    }
}
