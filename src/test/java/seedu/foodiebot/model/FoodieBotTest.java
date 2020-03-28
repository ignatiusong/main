package seedu.foodiebot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_NAME_DECK;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_TAG_ASIAN;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;
import static seedu.foodiebot.testutil.TypicalCanteens.getTypicalFoodieBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.canteen.exceptions.DuplicateCanteenException;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.randomize.Randomize;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.testutil.CanteenBuilder;

public class FoodieBotTest {

    private final FoodieBot foodieBot = new FoodieBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), foodieBot.getCanteenList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodieBot.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FoodieBot newData = getTypicalFoodieBot();
        foodieBot.resetData(newData);
        assertEquals(newData, foodieBot);
    }

    @Test
    public void resetData_withDuplicateCanteens_throwsDuplicateCanteenException() {
        // Two persons with the same identity fields
        Canteen editedAlice =
            new CanteenBuilder(DECK)
                .withName(VALID_NAME_DECK)
                .withTags(VALID_TAG_ASIAN)
                .build();
        List<Canteen> newCanteens = Arrays.asList(DECK, editedAlice);
        FoodieBotStub newData = new FoodieBotStub(newCanteens, new ArrayList<>(), new Budget());

        assertThrows(DuplicateCanteenException.class, () -> foodieBot.resetData(newData));
    }

    @Test
    public void hasCanteen_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodieBot.hasCanteen(null));
    }

    @Test
    public void hasCanteen_personNotInAddressBook_returnsFalse() {
        assertFalse(foodieBot.hasCanteen(DECK));
    }

    @Test
    public void hasCanteen_personInAddressBook_returnsTrue() {
        foodieBot.addCanteen(DECK);
        assertTrue(foodieBot.hasCanteen(DECK));
    }

    @Test
    public void hasCanteen_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        foodieBot.addCanteen(DECK);
        Canteen editedAlice =
            new CanteenBuilder(DECK)
                .withName(VALID_NAME_DECK)
                .withTags(VALID_TAG_ASIAN)
                .build();
        assertTrue(foodieBot.hasCanteen(editedAlice));
    }

    @Test
    public void getCanteenList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, () -> foodieBot.getCanteenList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class FoodieBotStub implements ReadOnlyFoodieBot {
        private final ObservableList<Canteen> canteens = FXCollections.observableArrayList();
        private final ObservableList<Stall> stalls = FXCollections.observableArrayList();
        private final ObservableList<Food> foods = FXCollections.observableArrayList();
        private final ObservableList<Food> favorites = FXCollections.observableArrayList();
        private final ObservableList<PurchasedFood> transactions = FXCollections.observableArrayList();
        private Budget budget;
        private Randomize randomize;

        FoodieBotStub(Collection<Canteen> canteens, Collection<Stall> stalls, Budget budget) {
            this.canteens.setAll(canteens);
            this.stalls.setAll(stalls);
            this.budget = budget;
            this.randomize = Randomize.checkRandomize();
        }

        @Override
        public ObservableList<Canteen> getCanteenList() {
            return canteens;
        }

        @Override
        public ObservableList<Stall> getStallList() {
            return stalls;
        }

        @Override
        public ObservableList<Food> getFoodList() {
            return foods;
        }

        @Override
        public ObservableList<Food> getFavoriteList() {
            return favorites;
        }

        @Override
        public Budget getBudget() {
            return budget;
        }

        @Override
        public ObservableList<PurchasedFood> getTransactionsList() {
            return transactions;
        }

        @Override
        public void removeFavorite(Food food) {
            //
        }

        @Override
        public boolean isLocationSpecified() {
            return false;
        }

        @Override
        public Randomize getRandomize() {
            return randomize;
        }
    }
}
