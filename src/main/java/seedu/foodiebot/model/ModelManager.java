package seedu.foodiebot.model;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.canteen.Canteen;

/** Represents the in-memory model of the address book data. */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FoodieBot foodieBot;
    private final UserPrefs userPrefs;
    private final FilteredList<Canteen> filteredCanteens;

    /** Initializes a ModelManager with the given addressBook and userPrefs. */
    public ModelManager(ReadOnlyFoodieBot foodieBot, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(foodieBot, userPrefs);

        logger.fine("Initializing with food data: " + foodieBot + " and user prefs " + userPrefs);

        this.foodieBot = new FoodieBot(foodieBot);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredCanteens = new FilteredList<>(this.foodieBot.getCanteenList());
    }

    public ModelManager() {
        this(new FoodieBot(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public void setFoodieBot(ReadOnlyFoodieBot foodieBot) {
        this.foodieBot.resetData(foodieBot);
    }

    @Override
    public ReadOnlyFoodieBot getFoodieBot() {
        return foodieBot;
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
        updateFilteredCanteenList(PREDICATE_SHOW_ALL_CANTEENS);
    }

    @Override
    public void setCanteen(Canteen target, Canteen editedCanteen) {
        requireAllNonNull(target, editedCanteen);

        foodieBot.setCanteen(target, editedCanteen);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Canteen> getFilteredCanteenList() {
        return filteredCanteens;
    }

    @Override
    public void updateFilteredCanteenList(Predicate<Canteen> predicate) {
        requireNonNull(predicate);
        filteredCanteens.setPredicate(predicate);
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
