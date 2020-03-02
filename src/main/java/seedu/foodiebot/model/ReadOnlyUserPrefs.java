package seedu.foodiebot.model;

import java.nio.file.Path;

import seedu.foodiebot.commons.core.GuiSettings;

/** Unmodifiable view of user prefs. */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFoodieBotFilePath();
}
