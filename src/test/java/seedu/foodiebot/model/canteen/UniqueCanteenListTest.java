package seedu.foodiebot.model.canteen;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_TAG_ASIAN;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;
import static seedu.foodiebot.testutil.TypicalCanteens.NUSFLAVORS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.exceptions.CanteenNotFoundException;
import seedu.foodiebot.model.canteen.exceptions.DuplicateCanteenException;
import seedu.foodiebot.testutil.CanteenBuilder;

public class UniqueCanteenListTest {

    private final UniqueCanteenList uniqueCanteenList = new UniqueCanteenList();

    @Test
    public void contains_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCanteenList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueCanteenList.contains(DECK));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueCanteenList.add(DECK);
        assertTrue(uniqueCanteenList.contains(DECK));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCanteenList.add(DECK);
        Canteen editedCanteen = new CanteenBuilder(DECK).withTags(VALID_TAG_ASIAN).build();
        assertTrue(uniqueCanteenList.contains(editedCanteen));
    }

    @Test
    public void add_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCanteenList.add(null));
    }

    @Test
    public void add_duplicateCanteen_throwsDuplicateCanteenException() {
        uniqueCanteenList.add(DECK);
        assertThrows(DuplicateCanteenException.class, () -> uniqueCanteenList.add(DECK));
    }

    @Test
    public void setCanteen_nullTargetCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCanteenList.setCanteen(null, DECK));
    }

    @Test
    public void setCanteen_nullEditedCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCanteenList.setCanteen(DECK, null));
    }

    @Test
    public void setCanteen_targetCanteenNotInList_throwsCanteenNotFoundException() {
        assertThrows(
            CanteenNotFoundException.class, () -> uniqueCanteenList.setCanteen(DECK, DECK));
    }

    @Test
    public void setCanteen_editedCanteenIsSameCanteen_success() {
        uniqueCanteenList.add(DECK);
        uniqueCanteenList.setCanteen(DECK, DECK);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        expectedUniqueCanteenList.add(DECK);
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteen_editedCanteenHasSameIdentity_success() {
        uniqueCanteenList.add(DECK);
        Canteen editedDeck = new CanteenBuilder(DECK).withTags(VALID_TAG_ASIAN).build();
        uniqueCanteenList.setCanteen(DECK, editedDeck);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        expectedUniqueCanteenList.add(editedDeck);
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteen_editedCanteenHasDifferentIdentity_success() {
        uniqueCanteenList.add(DECK);
        uniqueCanteenList.setCanteen(DECK, NUSFLAVORS);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        expectedUniqueCanteenList.add(NUSFLAVORS);
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteen_editedCanteenHasNonUniqueIdentity_throwsDuplicateCanteenException() {
        uniqueCanteenList.add(DECK);
        uniqueCanteenList.add(NUSFLAVORS);
        assertThrows(
            DuplicateCanteenException.class, (
            ) -> uniqueCanteenList.setCanteen(DECK, NUSFLAVORS));
    }

    @Test
    public void remove_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCanteenList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsCanteenNotFoundException() {
        assertThrows(CanteenNotFoundException.class, () -> uniqueCanteenList.remove(DECK));
    }

    @Test
    public void remove_existingCanteen_removesCanteen() {
        uniqueCanteenList.add(DECK);
        uniqueCanteenList.remove(DECK);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteen_nullUniqueCanteenList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueCanteenList.setCanteens((UniqueCanteenList) null));
    }

    @Test
    public void setCanteens_uniqueCanteenList_replacesOwnListWithProvidedUniqueCanteenList() {
        uniqueCanteenList.add(DECK);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        expectedUniqueCanteenList.add(NUSFLAVORS);
        uniqueCanteenList.setCanteens(expectedUniqueCanteenList);
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteens_nullList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueCanteenList.setCanteens((List<Canteen>) null));
    }

    @Test
    public void setCanteens_list_replacesOwnListWithProvidedList() {
        uniqueCanteenList.add(DECK);
        List<Canteen> itemList = Collections.singletonList(NUSFLAVORS);
        uniqueCanteenList.setCanteens(itemList);
        UniqueCanteenList expectedUniqueCanteenList = new UniqueCanteenList();
        expectedUniqueCanteenList.add(NUSFLAVORS);
        assertEquals(expectedUniqueCanteenList, uniqueCanteenList);
    }

    @Test
    public void setCanteens_listWithDuplicateCanteens_throwsDuplicateCanteenException() {
        List<Canteen> listWithDuplicateCanteens = Arrays.asList(DECK, DECK);
        assertThrows(
            DuplicateCanteenException.class, (
            ) -> uniqueCanteenList.setCanteens(listWithDuplicateCanteens));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> uniqueCanteenList.asUnmodifiableObservableList().remove(0));

        UniqueCanteenList canteenList = new UniqueCanteenList();
        assertDoesNotThrow(canteenList::iterator);
        assertDoesNotThrow(canteenList::hashCode);
    }
}
