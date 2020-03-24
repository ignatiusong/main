package seedu.foodiebot.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.foodiebot.commons.core.GuiSettings;

/** Represents User's preferences. */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path foodieBotFilePath = Paths.get("foodiebot.json");
    private Path stallsFilePath = Paths.get("foodiebot-stalls.json");
    private Path budgetFilePath = Paths.get("foodiebot-budget.json");
    private Path foodFilePath = Paths.get("foodiebot-food.json");
    private Path favoriteFoodFilePath = Paths.get("foodiebot-favorites.json");
    private Path transactionsFilePath = Paths.get("foodiebot-transactions.json");


    /** Creates a {@code UserPrefs} with default values. */
    public UserPrefs() {}

    /** Creates a {@code UserPrefs} with the prefs in {@code userPrefs}. */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /** Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}. */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setFoodieBotFilePath(newUserPrefs.getFoodieBotFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFoodieBotFilePath() {
        return foodieBotFilePath;
    }

    public Path getStallsFilePath() {
        return stallsFilePath;
    }

    public Path getFoodFilePath() {
        return foodFilePath;
    }

    public Path getBudgetFilePath() {
        return budgetFilePath;
    }

    public Path getFavoriteFoodFilePath() {
        return favoriteFoodFilePath;
    }

    public Path getTransactionsFilePath() {
        return transactionsFilePath;
    }

    public void setFoodieBotFilePath(Path foodieBotFilePath) {
        requireNonNull(foodieBotFilePath);
        this.foodieBotFilePath = foodieBotFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { // this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && foodieBotFilePath.equals(o.foodieBotFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, foodieBotFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + foodieBotFilePath);
        return sb.toString();
    }
}
