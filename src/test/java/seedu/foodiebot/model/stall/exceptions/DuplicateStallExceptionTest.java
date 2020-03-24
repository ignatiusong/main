package seedu.foodiebot.model.stall.exceptions;

import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.stall.UniqueStallList;
import seedu.foodiebot.model.util.SampleDataUtil;

class DuplicateStallExceptionTest {

    @Test
    public void exception_thrownOnDuplicateStall() {
        UniqueStallList stallList = new UniqueStallList();
        for (Stall stall : SampleDataUtil.getSampleStalls()) {
            stallList.add(stall);
        }
        assertThrows(DuplicateStallException.class, () -> stallList.add(new Stall(new Name("Chinese Cooked Food"),
            "The Deck", 1, "ChineseCookedFood.png", "asian",
            "$", 0, getTagSet("rice", "noodle", "cheap"), new ArrayList<>())));
    }
}
