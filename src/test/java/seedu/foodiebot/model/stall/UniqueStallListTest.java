package seedu.foodiebot.model.stall;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_TAG_ASIAN;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.stall.exceptions.DuplicateStallException;
import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.testutil.StallBuilder;

class UniqueStallListTest {

    private final UniqueStallList uniqueStallList = new UniqueStallList();
    private Stall validStall = SampleDataUtil.getSampleStalls()[0];

    @Test
    public void contains_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueStallList.contains(validStall));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueStallList.add(validStall);
        assertTrue(uniqueStallList.contains(validStall));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStallList.add(validStall);
        Stall editedStall = new StallBuilder(validStall).withTags(VALID_TAG_ASIAN).build();
        assertTrue(uniqueStallList.contains(editedStall));
    }

    @Test
    public void add_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.add(null));
    }

    @Test
    public void add_duplicateStall_throwsDuplicateStallException() {
        uniqueStallList.add(validStall);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.add(validStall));
    }

    @Test
    public void setStall_nullTargetStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(null, validStall));
    }

    @Test
    public void setStall_nullEditedStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(validStall, null));
    }

    @Test
    public void setStall_targetStallNotInList_throwsStallNotFoundException() {
        assertThrows(
            StallNotFoundException.class, () -> uniqueStallList.setStall(validStall, validStall));
    }

    @Test
    public void remove_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.remove(validStall));
    }

    @Test
    public void remove_existingStall_removesStall() {
        uniqueStallList.add(validStall);
        uniqueStallList.remove(validStall);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_nullUniqueStallList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueStallList.setStalls((UniqueStallList) null));
    }

    @Test
    public void setStalls_uniqueStallList_replacesOwnListWithProvidedUniqueStallList() {
        uniqueStallList.add(validStall);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(validStall);
        uniqueStallList.setStalls(expectedUniqueStallList);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueStallList.setStalls((List<Stall>) null));
    }

    @Test
    public void setStalls_list_replacesOwnListWithProvidedList() {
        uniqueStallList.add(validStall);
        List<Stall> itemList = Collections.singletonList(validStall);
        uniqueStallList.setStalls(itemList);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(validStall);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_listWithDuplicateStalls_throwsDuplicateStallException() {
        List<Stall> listWithDuplicateStalls = Arrays.asList(validStall, validStall);
        assertThrows(
            DuplicateStallException.class, (
            ) -> uniqueStallList.setStalls(listWithDuplicateStalls));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> uniqueStallList.asUnmodifiableObservableList().remove(0));

        UniqueStallList canteenList = new UniqueStallList();
        assertDoesNotThrow(canteenList::iterator);
        assertDoesNotThrow(canteenList::hashCode);
    }
}
