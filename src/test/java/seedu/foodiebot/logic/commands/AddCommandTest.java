package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.foodiebot.commons.core.GuiSettings;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.ReadOnlyUserPrefs;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.transaction.PurchasedFood;

public class AddCommandTest {

    /*
    @Test
    public void constructor_nullCanteen_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCanteenAdded modelStub = new ModelStubAcceptingCanteenAdded();
        Canteen validCanteen = new CanteenBuilder().build();

        CommandResult commandResult = new AddCommand(validCanteen).execute(modelStub);

        assertEquals(
                String.format(AddCommand.MESSAGE_SUCCESS, validCanteen),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCanteen), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicateCanteen_throwsCommandException() {
        Canteen validCanteen = new CanteenBuilder().build();
        AddCommand addCommand = new AddCommand(validCanteen);
        ModelStub modelStub = new ModelStubWithCanteen(validCanteen);

        assertThrows(
                CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_PERSON,
                () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Canteen alice = new CanteenBuilder().withName("Deck").build();
        Canteen bob = new CanteenBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
    */

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCanteen(Canteen person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFoodieBot getFoodieBot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodieBot(ReadOnlyFoodieBot newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCanteen(Canteen person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCanteen(Canteen target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCanteen(Canteen target, Canteen editedCanteen) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Canteen> getFilteredCanteenList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns an unmodifiable view of the filtered canteen list sorted by distance
         */
        @Override
        public ObservableList<Canteen> getFilteredCanteenListSortedByDistance() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocationSpecified(boolean isLocationSpecified) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCanteenList(Predicate<Canteen> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Stall> getFilteredStallList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Stall> getFilteredStallList(boolean isInitialised) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Budget> getBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FileReader listOfCanteens() throws FileNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public FileReader listOfStalls() throws FileNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the filter of the filtered stall list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredStallList(Predicate<Stall> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isLocationSpecified() {
            return false;
        }

        @Override
        public void setFavorite(Food food) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilteredFavoriteFoodList() {
            return null;
        }

        @Override
        public void updateFilteredFavoriteList(Predicate<Food> predicateShowAll) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Food> getFilteredFoodList() {
            throw new AssertionError("This method is a placeholder");
        }

        @Override
        public ObservableList<Food> getFilteredFoodList(boolean isInitialised) {
            return null;
        }

        @Override
        public void updateFilteredFoodList(Predicate<Food> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<PurchasedFood> getFilteredTransactionsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void loadFilteredTransactionsList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionsList(Predicate<PurchasedFood> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPurchasedFood(PurchasedFood food) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithCanteen extends ModelStub {
        private final Canteen person;

        ModelStubWithCanteen(Canteen person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasCanteen(Canteen person) {
            requireNonNull(person);
            return this.person.isSameCanteen(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingCanteenAdded extends ModelStub {
        final ArrayList<Canteen> personsAdded = new ArrayList<>();

        @Override
        public boolean hasCanteen(Canteen person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameCanteen);
        }

        @Override
        public void addCanteen(Canteen person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyFoodieBot getFoodieBot() {
            return new FoodieBot();
        }
    }
}
