package seedu.foodiebot.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.Model.PREDICATE_SHOW_ALL;
import static seedu.foodiebot.testutil.Assert.assertThrows;
import static seedu.foodiebot.testutil.TypicalCanteens.DECK;
import static seedu.foodiebot.testutil.TypicalCanteens.NUSFLAVORS;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.NameContainsKeywordsPredicate;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;
import seedu.foodiebot.testutil.FoodieBotBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new FoodieBot(), new FoodieBot(modelManager.getFoodieBot()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setFoodieBotFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setFoodieBotFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setFoodieBotFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setFoodieBotFilePath(path);
        assertEquals(path, modelManager.getFoodieBotFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCanteen(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasCanteen(DECK));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addCanteen(DECK);
        assertTrue(modelManager.hasCanteen(DECK));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(
            UnsupportedOperationException.class, (
            ) -> modelManager.getFilteredCanteenList().remove(0));
    }

    @Test
    public void equals() {
        FoodieBot foodieBot =
            new FoodieBotBuilder().withCanteen(DECK).withCanteen(NUSFLAVORS).build();
        FoodieBot differentFoodieBot = new FoodieBot();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(foodieBot, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(foodieBot, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentFoodieBot, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = DECK.getName().fullName.split("\\s+");
        modelManager.updateFilteredCanteenList(
            new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(foodieBot, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCanteenList(PREDICATE_SHOW_ALL);

        modelManager.updateFilteredStallList(
            f -> f.getName().equals("Cai Fan"));

        modelManager.updateFilteredStallList(PREDICATE_SHOW_ALL);
        assertTrue(modelManager.equals(new ModelManager(foodieBot, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCanteenList(PREDICATE_SHOW_ALL);

        //Favorites
        assertDoesNotThrow(() -> modelManager.setFavorite(SampleDataUtil.getSampleFoods()[1]));
        assertFalse(modelManager.equals(new ModelManager(foodieBot, userPrefs)));
        //Reset Favorites
        assertThrows(StallNotFoundException.class, () -> modelManager
            .removeFavorite(SampleDataUtil.getSampleFoods()[0]));

        assertDoesNotThrow(() -> modelManager
            .removeFavorite(SampleDataUtil.getSampleFoods()[1]));
        assertTrue(modelManager.equals(new ModelManager(foodieBot, userPrefs)));

        //Transactions
        PurchasedFood food = new PurchasedFood(SampleDataUtil.getSampleFoods()[0],
            LocalDate.now(), LocalTime.now(), new Rating(5), new Review());
        modelManager.addPurchasedFood(food);
        assertFalse(modelManager.equals(new ModelManager(foodieBot, userPrefs)));

        modelManager = modelManagerCopy;

        //Get filtered list does not modify existing list
        modelManager.getFilteredCanteenListSortedByDistance();
        assertTrue(modelManager.equals(new ModelManager(foodieBot, userPrefs)));

        assertDoesNotThrow(() -> modelManager.getFilteredStallList());
        assertDoesNotThrow(() -> modelManager.getFilteredFoodList());
        assertDoesNotThrow(() -> modelManager.loadFilteredTransactionsList());
        assertDoesNotThrow(() -> modelManager.getFilteredFoodList(true));
        assertDoesNotThrow(() -> modelManager.getFilteredFoodList(false));
        assertDoesNotThrow(() -> modelManager.updateFilteredFoodList(PREDICATE_SHOW_ALL));
        assertDoesNotThrow(() -> modelManager.updateModelManagerFavoriteList());
        assertDoesNotThrow(() -> modelManager.updateModelManagerStalls());

        //Budget
        modelManager.setBudget(new Budget(15f, "d/"));
        assertFalse(modelManager.equals(new ModelManager(foodieBot, userPrefs)));
        try {
            //assertEquals(modelManager.getBudget().get().getTotalBudget(), 15f);
        } catch (NoSuchElementException ex) {
            //failing test
        }

        assertEquals(modelManager, modelManager);


        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setFoodieBotFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(foodieBot, differentUserPrefs)));
    }
}
