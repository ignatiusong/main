package seedu.foodiebot.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.ReadOnlyUserPrefs;
import seedu.foodiebot.model.UserPrefs;

/** API of the Storage component */
public interface Storage extends FoodieBotStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //Path getFoodieBotFilePath();


    @Override
    void saveFoodieBot(ReadOnlyFoodieBot foodieBot) throws IOException;
}
