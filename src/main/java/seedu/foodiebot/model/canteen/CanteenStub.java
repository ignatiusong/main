package seedu.foodiebot.model.canteen;

import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.foodiebot.model.tag.Tag;

/**
 * FoodieBot does not allow new canteens to be added/ edited yet. This stub is used by
 *
 * @code AddCommandParser to recognise only the relevant prefix. Guarantees: details are present and
 *     not null, field values are validated, immutable.
 */
public class CanteenStub {

    // Identity fields
    private final Name name;

    private final int numberOfStalls;

    // Data fields
    private final Set<Tag> cuisines = new HashSet<>();

    /** Every field must be present and not null. */
    public CanteenStub(Name name, int numberOfStalls, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.numberOfStalls = numberOfStalls;
        this.cuisines.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Set<Tag> getCuisines() {
        return cuisines;
    }

    public int getNumberOfStalls() {
        return numberOfStalls;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
     * modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(cuisines);
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger
     * notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CanteenStub)) {
            return false;
        }

        CanteenStub otherCanteen = (CanteenStub) other;
        return otherCanteen.getName().equals(getName()) && otherCanteen.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, cuisines);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append(" Name: ").append(getName())
            .append(" NumberOfStalls: ")
                .append(getNumberOfStalls()).append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
