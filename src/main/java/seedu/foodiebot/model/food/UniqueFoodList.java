package seedu.foodiebot.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.foodiebot.model.canteen.exceptions.DuplicateCanteenException;
import seedu.foodiebot.model.stall.exceptions.DuplicateStallException;
import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;



/**
 * List of Food Available while ensuring no duplicates
 */
public class UniqueFoodList implements Iterable<Food> {
    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final ObservableList<Food> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a food to the list. The food must not already exist in the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStallException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the canteen {@code target} in the list with {@code editedFood}. {@code target}
     * must exist in the list. The canteen identity of {@code editedFood} must not be the same as
     * another existing canteen in the list.
     */
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StallNotFoundException();
        }

        if (!target.isSameFood(editedFood) && contains(editedFood)) {
            throw new DuplicateStallException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedPerson}. {@code target}
     * must exist in the list. The food identity of {@code editedPerson} must not be the same as
     * another existing food in the list.
     */
    public void setFood(List<Food> food) {
        requireAllNonNull(food);
        if (!foodsAreUnique(food)) {
            throw new DuplicateCanteenException();
        }

        internalList.setAll(food);
    }

    /**
     * Removes the equivalent food from the list. The food must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StallNotFoundException();
        }
    }

    public void setFoods(UniqueFoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Food> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueFoodList // instanceof handles nulls
            && internalList.equals(((UniqueFoodList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code food} contains only unique foods.
     */
    private boolean foodsAreUnique(List<Food> foods) {
        for (int i = 0; i < foods.size() - 1; i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).isSameFood(foods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

