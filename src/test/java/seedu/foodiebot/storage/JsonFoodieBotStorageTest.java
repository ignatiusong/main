package seedu.foodiebot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.NUSFLAVORS;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.foodiebot.commons.exceptions.DataConversionException;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;

public class JsonFoodieBotStorageTest {
    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonFoodieBotStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyFoodieBot> readAddressBook(String filePath)
        throws Exception {
        return new JsonFoodieBotStorage(Paths.get(filePath))
            .readFoodieBot(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(
            DataConversionException.class, (
            ) -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(
            DataConversionException.class, (
            ) -> readAddressBook("invalidPersonAddressBook.json"));
    }

    //   @Test
    //   public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
    //       assertThrows(
    //               DataConversionException.class,
    //               () -> readAddressBook("invalidAndValidCanteenFoodieBot.json"));
    //   }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FoodieBot original = getTypicalFoodieBot();
        JsonFoodieBotStorage jsonAddressBookStorage = new JsonFoodieBotStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        ReadOnlyFoodieBot readBack = jsonAddressBookStorage.readFoodieBot(filePath).get();
        assertEquals(original, new FoodieBot(readBack));

        // Modify data, overwrite exiting file, and read back
        original.removeCanteen(NUSFLAVORS);
        original.addCanteen(NUSFLAVORS);
        jsonAddressBookStorage.saveAddressBook(original, filePath);
        readBack = jsonAddressBookStorage.readFoodieBot(filePath).get();
        assertEquals(original, new FoodieBot(readBack));

        // Save and read without specifying file path
        //original.addCanteen(DECK);
        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
        readBack = jsonAddressBookStorage.readFoodieBot().get(); // file path not specified
        assertEquals(original, new FoodieBot(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyFoodieBot addressBook, String filePath) {
        try {
            new JsonFoodieBotStorage(Paths.get(filePath))
                .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new FoodieBot(), null));
    }
}
