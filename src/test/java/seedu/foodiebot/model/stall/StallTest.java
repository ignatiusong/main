package seedu.foodiebot.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.util.SampleDataUtil;

public class StallTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stall(null, "The Deck", 1, "ChineseCookedFood.png",
            "asian",
            "$", 0, getTagSet("rice", "noodle", "cheap"), new ArrayList<>()));
    }

    @Test
    public void are_stalls_equal() {
        Stall stall = SampleDataUtil.getSampleStalls()[0];
        Stall copy = SampleDataUtil.getSampleStalls()[0];

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(stall.hashCode(), copy.hashCode());

        assertFalse(stall.equals(canteen));

        assertTrue(stall.isSameStall(stall));
    }
}
