package seedu.foodiebot.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.model.canteen.Canteen;

/** The API of the Model component. */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Canteen> PREDICATE_SHOW_ALL_CANTEENS = unused -> true;

    /** Replaces user prefs data with the data in {@code userPrefs}. */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /** Returns the user prefs. */
    ReadOnlyUserPrefs getUserPrefs();

    /** Returns the user prefs' GUI settings. */
    GuiSettings getGuiSettings();

    /** Sets the user prefs' GUI settings. */
    void setGuiSettings(GuiSettings guiSettings);

    /** Returns the user prefs' address book file path. */
    Path getAddressBookFilePath();

    /** Sets the user prefs' address book file path. */
    void setAddressBookFilePath(Path addressBookFilePath);

    /** Replaces address book data with the data in {@code addressBook}. */
    void setFoodieBot(ReadOnlyFoodieBot foodieBot);

    /** Returns the AddressBook */
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
     * Replaces the given canteen {@code target} with {@code editedPerson}. {@code target} must
     * exist in the address book. The canteen identity of {@code editedPerson} must not be the same
     * as another existing canteen in the address book.
     */
    void setCanteen(Canteen target, Canteen editedCanteen);

    /** Returns an unmodifiable view of the filtered canteen list */
    ObservableList<Canteen> getFilteredCanteenList();

    /**
     * Updates the filter of the filtered canteen list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCanteenList(Predicate<Canteen> predicate);
}
