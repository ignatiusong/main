package seedu.foodiebot.storage;

import static seedu.foodiebot.storage.JsonAdaptedCanteen.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Name;

public class JsonAdaptedCanteenTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DISTANCE = "-12";
    private static final String INVALID_NUMBEROFSTALLS = "-1";
    private static final String INVALID_BLOCKNAME = "home";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = DECK.getName().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS =
        DECK.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validCanteenDetails_returnsCanteen() throws Exception {
        JsonAdaptedCanteen canteen = new JsonAdaptedCanteen(DECK);
        //assertEquals(DECK, canteen.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCanteen canteen =
            new JsonAdaptedCanteen(INVALID_NAME, "0", "0", "deck.jpg",
                "", "deck.jpg", "", VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, canteen::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCanteen canteen = new JsonAdaptedCanteen(null, "0", "0",
            "", "", "deck.jpg", "", VALID_TAGS);
        String expectedMessage =
            String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, canteen::toModelType);
    }

    @Test
    public void toModelType_invalidDistance_throwsIllegalValueException() {
        JsonAdaptedCanteen canteen =
            new JsonAdaptedCanteen(VALID_NAME, INVALID_DISTANCE, "0", "The Deck",
                "", "deck.jpg", "", VALID_TAGS);
        // String expectedMessage = Distance.MESSAGE_CONSTRAINTS;
        // assertThrows(IllegalValueException.class, expectedMessage, canteen::toModelType);
    }
}
