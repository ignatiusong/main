package seedu.foodiebot.model.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;
import seedu.foodiebot.model.util.SampleDataUtil;

class PurchasedFoodListTest {
    private final PurchasedFoodList foodList = new PurchasedFoodList();
    private PurchasedFood food = new PurchasedFood(SampleDataUtil.getSampleFoods()[0],
        LocalDate.now(), LocalTime.now(), new Rating(5), new Review());
    private PurchasedFood food2 = new PurchasedFood(SampleDataUtil.getSampleFoods()[0],
        LocalDate.now(), LocalTime.now(), new Rating(5), new Review());

    @Test
    public void contains_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.contains(null));
    }

    @Test
    public void contains_itemNotInList_returnsFalse() {
        assertFalse(foodList.contains(food));
    }

    @Test
    public void contains_itemInList_returnsTrue() {
        foodList.add(food);
        assertTrue(foodList.contains(food));
    }

    @Test
    public void contains_itemWithSameIdentityFieldsInList_returnsTrue() {
        foodList.add(food);
        assertTrue(foodList.contains(food2));
    }

    @Test
    public void add_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.add(null));
    }

    @Test
    public void add_duplicateCanteen_throwsDuplicateCanteenException() {
        foodList.add(food);
        assertDoesNotThrow(() -> foodList.add(food));
    }

    @Test
    public void setFood_nullTargetCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood(null, food));
    }

    @Test
    public void setFood_nullEditedCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.setFood(food, null));
    }

    @Test
    public void setFood_targetCanteenNotInList_throwsCanteenNotFoundException() {
        assertThrows(
            StallNotFoundException.class, () -> foodList.setFood(food, food));
    }

    public void setFood_editedCanteenHasDifferentIdentity_success() {
        foodList.add(food);
        foodList.setFood(food, food2);
        PurchasedFoodList expectedPurchasedFoodList = new PurchasedFoodList();
        expectedPurchasedFoodList.add(food2);
        assertEquals(expectedPurchasedFoodList, foodList);
    }
    @Test
    public void remove_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodList.remove(null));
    }

    @Test
    public void remove_itemDoesNotExist_throwsCanteenNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> foodList.remove(food));
    }

    @Test
    public void remove_existingCanteen_removesCanteen() {
        foodList.add(food);
        foodList.remove(food);
        PurchasedFoodList expectedPurchasedFoodList = new PurchasedFoodList();
        assertEquals(expectedPurchasedFoodList, foodList);
    }

    @Test
    public void setFood_nullPurchasedFoodList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> foodList.setFoods((PurchasedFoodList) null));
    }

    @Test
    public void setFoods_nullList_throwsNullPointerException() {
        assertThrows(
            NullPointerException.class, (
            ) -> foodList.setFoods((List<PurchasedFood>) null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> foodList.asUnmodifiableObservableList().remove(0));

        PurchasedFoodList canteenList = new PurchasedFoodList();
        assertDoesNotThrow(canteenList::iterator);
        assertDoesNotThrow(canteenList::hashCode);
    }
}
