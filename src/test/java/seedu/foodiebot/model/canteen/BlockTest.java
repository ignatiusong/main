package seedu.foodiebot.model.canteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class BlockTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Block(new Name(null)));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Block(new Name(invalidName)));
    }

    @Test
    public void isValidBlock() {
        // null name
        assertThrows(NullPointerException.class, () -> Block.isValidBlock(null));

        Block block = new Block(new Name("com1"));
        assertEquals(block.getName().toString(), "com1");
        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("com1*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("pgpr")); // alphabets only
        assertTrue(Name.isValidName("1")); // numbers only
        assertTrue(Name.isValidName("com1")); // alphanumeric characters
        assertTrue(Name.isValidName("COM1")); // with capital letters
        assertTrue(Name.isValidName("Nus Flavors")); // long names
    }
}
