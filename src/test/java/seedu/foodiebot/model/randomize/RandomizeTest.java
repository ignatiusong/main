package seedu.foodiebot.model.randomize;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.testutil.TypicalCanteens;

class RandomizeTest {

    private static FileReader canteens = null;
    private static FileReader stalls = null;

    public ModelManager createSampleModel() {
        return new ModelManager(TypicalCanteens.getTypicalFoodieBot(), new UserPrefs());
    }

    @Test
    public void are_randomize_equal() throws CommandException, FileNotFoundException {
        Randomize randomize = Randomize.checkRandomize();
        Randomize copy = Randomize.checkRandomize();
        assertNotNull(randomize);

        randomize.setCanteens(createSampleModel().listOfCanteens());
        assertThrows(NullPointerException.class, () -> randomize.setCanteens(null));
        randomize.setCanteenIndex(SampleDataUtil.getSampleCanteens()[0]);
        //assertNotEquals(randomize, copy);
        assertEquals(copy.hashCode(), Randomize.checkRandomize().hashCode());


        //assertDoesNotThrow(() -> randomize.getOptionsByCanteen(createSampleModel().listOfCanteens()));
        assertThrows(NullPointerException.class, () -> randomize.getOptionsByCanteen(null));
        assertThrows(FileNotFoundException.class, () -> randomize.getOptionsByCanteen(
            new FileReader("invalid.json")));
        assertDoesNotThrow(() -> randomize.getOptionsByCanteen(createSampleModel().listOfStalls()));


        //assertDoesNotThrow(() -> randomize.getOptionsByTags(createSampleModel().listOfStalls()));
        assertThrows(NullPointerException.class, () -> randomize.getOptionsByTags(null));
        assertThrows(FileNotFoundException.class, () -> randomize.getOptionsByTags(
            new FileReader("invalid.json")));
    }
}
