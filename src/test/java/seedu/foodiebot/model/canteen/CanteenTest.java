package seedu.foodiebot.model.canteen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.util.SampleDataUtil.COM1_TO_NUSFLAVORS_DIRECTIONS;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.util.SampleDataUtil;

class CanteenTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Canteen(
            null,
            11,
            3200,
            "COM1",
            "utown_flavors.png",
            COM1_TO_NUSFLAVORS_DIRECTIONS,
            getTagSet("asian", "western", "muslim"), "utown.jpg", new ArrayList<>()));
    }

    @Test
    public void are_canteens_equal() {
        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        Canteen copy = SampleDataUtil.getSampleCanteens()[0];

        Stall stall = SampleDataUtil.getSampleStalls()[0];
        assertEquals(canteen.hashCode(), copy.hashCode());

        assertFalse(canteen.equals(stall));

        Canteen stallNumber = new Canteen(
            new Name("Nus Flavors"),
            10,
            3200,
            "COM1",
            "utown_flavors.png",
            COM1_TO_NUSFLAVORS_DIRECTIONS,
            getTagSet("asian", "western", "muslim"), "utown.jpg", new ArrayList<>());

        Canteen differentStallNumber = new Canteen(
            new Name("Nus Flavors"),
            11,
            3200,
            "COM1",
            "utown_flavors.png",
            COM1_TO_NUSFLAVORS_DIRECTIONS,
            getTagSet("asian", "western", "muslim"), "utown.jpg", new ArrayList<>());

        assertTrue(stallNumber.isSameCanteen(differentStallNumber));
    }
}
