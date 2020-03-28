package seedu.foodiebot.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.transaction.PurchasedFood;

/** The API of the Model component. */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate PREDICATE_SHOW_ALL = unused -> true;

    /** Replaces user prefs data with the data in {@code userPrefs}. */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /** Returns the user prefs. */
    ReadOnlyUserPrefs getUserPrefs();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Sets the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns the user prefs' address book file path. */
    Path getFoodieBotFilePath();

    /** Sets the user prefs' address book file path. */
    void setFoodieBotFilePath(Path foodieBotFilePath);

    /** Replaces address book data with the data in {@code foodieBot}. */
    void setFoodieBot(ReadOnlyFoodieBot foodieBot);

    /** Returns the FoodieBot */
    ReadOnlyFoodieBot getFoodieBot();

    /**
     * Returns true if a canteen with the same identity as {@code canteen} exists in the address
     * book.
     */
    boolean hasCanteen(Canteen canteen);

    /** Deletes the given canteen. The canteen must exist in the address book. */
    void deleteCanteen(Canteen target);

    /** Adds the given canteen. {@code canteen} must not already exist in the address book. */
    void addCanteen(Canteen canteen);

    /**
     * Replaces the given canteen {@code target} with {@code editedCanteen}. {@code target} must
     * exist in the address book. The canteen identity of {@code editedCanteen} must not be the same
     * as another existing canteen in the address book.
     */
    void setCanteen(Canteen target, Canteen editedCanteen);

    void setBudget(Budget budget);

    Optional<Budget> getBudget();

    /** Return a FileReader of the list of canteen in json file */
    FileReader listOfCanteens() throws FileNotFoundException;

    /** Return a FileReader of the list of Stalls in json file */
    FileReader listOfStalls() throws FileNotFoundException;

    /** Returns an unmodifiable view of the filtered canteen list */
    ObservableList<Canteen> getFilteredCanteenList();

    /** Returns an unmodifiable view of the filtered canteen list sorted by distance */
    ObservableList<Canteen> getFilteredCanteenListSortedByDistance();
    void setLocationSpecified(boolean isLocationSpecified);

    /**
     * Updates the filter of the filtered canteen list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCanteenList(Predicate<Canteen> predicate);


    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredStallList();

    /** Returns an unmodifiable view of the filtered stall list */
    ObservableList<Stall> getFilteredStallList(boolean isInitialised);
    /**
     * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStallList(Predicate<Stall> predicate);

    /** Returns an unmodifiable view of the filtered food list */
    ObservableList<Food> getFilteredFoodList();

    /** Returns an unmodifiable view of the filtered food list */
    ObservableList<Food> getFilteredFoodList(boolean isInitialised);

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);

    boolean isLocationSpecified();

    void setFavorite(Food food);

    ObservableList<Food> getFilteredFavoriteFoodList(boolean isInitialised);

    void updateFilteredFavoriteList(Predicate<Food> predicateShowAll);

    ObservableList<PurchasedFood> getFilteredTransactionsList();

    void loadFilteredTransactionsList();

    void updateFilteredTransactionsList(Predicate<PurchasedFood> predicate);

    void addPurchasedFood(PurchasedFood food);

    ObservableList<Stall> getFilteredRandomizeList();

    void removeFavorite(Food food);

    void setFavoriteList(ObservableList<Food> filteredFavoriteFoodList);
}
