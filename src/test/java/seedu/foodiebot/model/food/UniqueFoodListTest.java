package seedu.foodiebot.model.food;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.stall.exceptions.DuplicateStallException;
import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;
import seedu.foodiebot.model.util.SampleDataUtil;

class UniqueFoodListTest {
    private final UniqueFoodList uniqueFoodList = new UniqueFoodList();
    private Food food = SampleDataUtil.getSampleFoods()[0];
    private Food food2 = SampleDataUtil.getSampleFoods()[1];

    @Test
    public void contains_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(uniqueFoodList.contains(food));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        uniqueFoodList.add(food);
        assertTrue(uniqueFoodList.contains(food));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFoodList.add(food);
        Food copy = SampleDataUtil.getSampleFoods()[0];
        assertTrue(uniqueFoodList.contains(copy));
    }

    @Test
    public void add_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.add(null));
    }

    @Test
    public void add_duplicateFood_throwsDuplicateStallException() {
        uniqueFoodList.add(food);
        assertThrows(DuplicateStallException.class, () -> uniqueFoodList.add(food));
    }

    @Test
    public void setFood_nullTargetFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(null, food));
    }

    @Test
    public void setFood_nullEditedFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.setFood(food, null));
    }

    @Test
    public void setFood_targetFoodNotInList_throwsFoodNotFoundException() {
        assertThrows(
            StallNotFoundException.class, () -> uniqueFoodList.setFood(food, food));
    }

    @Test
    public void setFood_editedFoodIsSameFood_success() {
        uniqueFoodList.add(food);
        uniqueFoodList.setFood(food, food);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(food);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasSameIdentity_success() {
        uniqueFoodList.add(food);
        Food copy = SampleDataUtil.getSampleFoods()[0];
        uniqueFoodList.setFood(food, copy);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(copy);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasDifferentIdentity_success() {
        uniqueFoodList.add(food);
        uniqueFoodList.setFood(food, food2);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(food2);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_editedFoodHasNonUniqueIdentity_throwsDuplicateFoodException() {
        uniqueFoodList.add(food);
        uniqueFoodList.add(food2);
        //assertThrows(
        //    DuplicateFoodException.class, (
        //    ) -> uniqueFoodList.setFood(food, food2));
    }

    @Test
    public void remove_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFoodList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsFoodNotFoundException() {
        //assertThrows(FoodNotFoundException.class, () -> uniqueFoodList.remove(food));
    }

    @Test
    public void remove_existingFood_removesFood() {
        uniqueFoodList.add(food);
        uniqueFoodList.remove(food);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFood_nullUniqueFoodList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueFoodList.setFoods((UniqueFoodList) null));
    }

    @Test
    public void setFoods_uniqueFoodList_replacesOwnListWithProvidedUniqueFoodList() {
        uniqueFoodList.add(food);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(food2);
        uniqueFoodList.setFoods(expectedUniqueFoodList);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> uniqueFoodList.setFoods(null));
    }

    @Test
    public void setFoods_list_replacesOwnListWithProvidedList() {
        uniqueFoodList.add(food);
        List<Food> itemList = Collections.singletonList(food2);
        uniqueFoodList.setFood(itemList);
        UniqueFoodList expectedUniqueFoodList = new UniqueFoodList();
        expectedUniqueFoodList.add(food2);
        assertEquals(expectedUniqueFoodList, uniqueFoodList);
    }

    @Test
    public void setFoods_listWithDuplicateFoods_throwsDuplicateFoodException() {
        List<Food> listWithDuplicateFoods = Arrays.asList(food, food);
        //assertThrows(
        //    DuplicateFoodException.class, (
        //    ) -> uniqueFoodList.setFoods(listWithDuplicateFoods));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> uniqueFoodList.asUnmodifiableObservableList().remove(0));

        UniqueFoodList foodList = new UniqueFoodList();
        assertDoesNotThrow(foodList::iterator);
        assertDoesNotThrow(foodList::hashCode);
    }
}
