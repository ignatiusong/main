package seedu.foodiebot.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.foodiebot.model.stall.exceptions.StallNotFoundException;

/**
 * List of Food Available
 */
public class PurchasedFoodList implements Iterable<PurchasedFood> {
    private final ObservableList<PurchasedFood> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(PurchasedFood toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a food to the list.
     */
    public void add(PurchasedFood toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Adds a food to the list in reverse order.
     */
    public void addReverse(PurchasedFood toAdd) {
        add(toAdd);
        internalList.sort((a, b) -> b.getDateAdded().atTime(b.getTimeAdded())
                .compareTo(a.getDateAdded().atTime(a.getTimeAdded())));
    }


    /**
     * Removes the equivalent food from the list. The food must exist in the list.
     */
    public void remove(PurchasedFood toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StallNotFoundException();
        }
    }

    /**
     * Removes all food in this list.
     */
    public void removeAll() {
        internalList.clear();
    }

    /**
     * Replaces the canteen {@code target} in the list with {@code editedFood}. {@code target}
     * must exist in the list.
     */
    public void setFood(PurchasedFood target, PurchasedFood editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StallNotFoundException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * @param replacement
     */
    public void setFoods(PurchasedFoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code food}.
     */
    public void setFoods(List<PurchasedFood> foods) {
        requireAllNonNull(foods);
        internalList.setAll(foods);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PurchasedFood> getList() {
        internalList.sort((a, b) -> b.getDateAdded().atTime(b.getTimeAdded())
                .compareTo(a.getDateAdded().atTime(a.getTimeAdded())));
        return internalList;
    }

    @Override
    public Iterator<PurchasedFood> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PurchasedFoodList // instanceof handles nulls
                && internalList.equals(((PurchasedFoodList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }







}
