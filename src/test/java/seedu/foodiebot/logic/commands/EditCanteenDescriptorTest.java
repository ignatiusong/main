package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.DESC_DECK;
import static seedu.foodiebot.logic.commands.CommandTestUtil.DESC_NUSFLAVORS;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_NAME_NUSFLAVORS;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_TAG_ASIAN;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.testutil.EditCanteenDescriptorBuilder;

public class EditCanteenDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditCanteenDescriptor descriptorWithSameValues =
            new EditCommand.EditCanteenDescriptor(DESC_DECK);
        //assertTrue(DESC_DECK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DECK.equals(DESC_DECK));

        // null -> returns false
        assertFalse(DESC_DECK.equals(null));

        // different types -> returns false
        assertFalse(DESC_DECK.equals(5));

        // different values -> returns false
        assertFalse(DESC_DECK.equals(DESC_NUSFLAVORS));

        // different name -> returns false
        EditCommand.EditCanteenDescriptor editCanteen =
            new EditCanteenDescriptorBuilder(DESC_DECK).withName(VALID_NAME_NUSFLAVORS).build();
        assertFalse(DESC_DECK.equals(editCanteen));

        // different tags -> returns false
        editCanteen = new EditCanteenDescriptorBuilder(DESC_DECK).withTags(VALID_TAG_ASIAN).build();
        //assertFalse(DESC_DECK.equals(editedAmy));
    }
}
