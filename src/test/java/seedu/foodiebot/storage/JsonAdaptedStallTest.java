package seedu.foodiebot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodiebot.storage.JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;
import static seedu.foodiebot.testutil.TypicalStalls.MUSLIM;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Name;

public class JsonAdaptedStallTest {
    private static final String VALID_CANTEEN_NAME = DECK.getName().toString();

    @Test
    public void toModelType_validStallDetails_returnsStall() throws Exception {
        JsonAdaptedStall stall = new JsonAdaptedStall("Muslim", "The Deck",
            "5", "taiwanese.png", "muslim",
            "$", "0", getTagSet("spciy", "rice", "halal", "cheap"));
        assertEquals(MUSLIM, stall.toModelType());
    }

    //    @Test
    //    public void toModelType_invalidName_throwsIllegalValueException() {
    //        JsonAdaptedStall stall = new JsonAdaptedStall("T@iwanese", DECK.toString(),
    //            "5", "taiwanese.png", "asian",
    //            "$", "0");
    //        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    //    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(null, DECK.toString(),
            "5", "taiwanese.png", "asian",
            "$", "0", getTagSet("spicy"));
        String expectedMessage =
            String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<JsonAdaptedTag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(JsonAdaptedTag::new).collect(Collectors.toSet());
    }

}
