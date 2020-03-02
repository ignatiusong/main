package seedu.foodiebot.model.canteen;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.foodiebot.model.canteen.exceptions.CanteenNotFoundException;
import seedu.foodiebot.model.canteen.exceptions.DuplicateCanteenException;

/**
 * A list of canteens that enforces uniqueness between its elements and does not allow nulls. A
 * canteen is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such,
 * adding and updating of canteens uses Person#isSamePerson(Person) for equality so as to ensure
 * that the canteen being added or updated is unique in terms of identity in the UniquePersonList.
 * However, the removal of a canteen uses Person#equals(Object) so as to ensure that the canteen
 * with exactly the same fields will be removed.
 *
 * <p>Supports a minimal set of list operations.
 *
 * @see Canteen#isSameCanteen(Canteen)
 */
public class UniqueCanteenList implements Iterable<Canteen> {

    private final ObservableList<Canteen> internalList = FXCollections.observableArrayList();
    private final ObservableList<Canteen> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /** Returns true if the list contains an equivalent canteen as the given argument. */
    public boolean contains(Canteen toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCanteen);
    }

    /** Adds a canteen to the list. The canteen must not already exist in the list. */
    public void add(Canteen toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCanteenException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the canteen {@code target} in the list with {@code editedPerson}. {@code target}
     * must exist in the list. The canteen identity of {@code editedPerson} must not be the same as
     * another existing canteen in the list.
     */
    public void setCanteen(Canteen target, Canteen editedCanteen) {
        requireAllNonNull(target, editedCanteen);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CanteenNotFoundException();
        }

        if (!target.isSameCanteen(editedCanteen) && contains(editedCanteen)) {
            throw new DuplicateCanteenException();
        }

        internalList.set(index, editedCanteen);
    }

    /** Removes the equivalent canteen from the list. The canteen must exist in the list. */
    public void remove(Canteen toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CanteenNotFoundException();
        }
    }

    public void setCanteens(UniqueCanteenList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code canteens}. {@code canteens} must not contain
     * duplicate canteens.
     */
    public void setCanteens(List<Canteen> canteens) {
        requireAllNonNull(canteens);
        if (!canteensAreUnique(canteens)) {
            throw new DuplicateCanteenException();
        }

        internalList.setAll(canteens);
    }

    /** Returns the backing list as an unmodifiable {@code ObservableList}. */
    public ObservableList<Canteen> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Canteen> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCanteenList // instanceof handles nulls
                        && internalList.equals(((UniqueCanteenList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /** Returns true if {@code canteens} contains only unique canteens. */
    private boolean canteensAreUnique(List<Canteen> canteens) {
        for (int i = 0; i < canteens.size() - 1; i++) {
            for (int j = i + 1; j < canteens.size(); j++) {
                if (canteens.get(i).isSameCanteen(canteens.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
