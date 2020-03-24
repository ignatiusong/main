package seedu.foodiebot.model.stall.exceptions;

import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.stall.UniqueStallList;
import seedu.foodiebot.model.util.SampleDataUtil;

class StallNotFoundExceptionTest {

    @Test
    public void exception_thrownOnInvalidStall() {
        UniqueStallList stallList = new UniqueStallList();
        Stall[] stalls = SampleDataUtil.getSampleStalls();
        for (Stall stall : stalls) {
            stallList.add(stall);
        }
        assertThrows(StallNotFoundException.class, () -> stallList.remove(new Stall(new Name("Taiwanee"),
            "The Deck", 3, "muslim.png",
            "asian",
            "$", 0, getTagSet("expensive", "asian"), new ArrayList<>())));
    }
}
