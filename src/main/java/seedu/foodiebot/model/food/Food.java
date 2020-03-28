package seedu.foodiebot.model.food;

import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.scene.image.Image;
import seedu.foodiebot.model.tag.Tag;

/**
 * Represents a Food in the address book. Guarantees: details are present and not null, field values
 * are validated, immutable.
 */
public class Food {
    public static final String[] FOOD = new String[]{"Combo Set", "Ayam Penyet", "Cai fan", "Fried Fish"};
    public static final String IMAGE_FOLDER = "/images/canteen/";
    public static final String MESSAGE_CONSTRAINTS = "Food name should be from " + Arrays.toString(FOOD);
    public static final String INVALID_FOOD_INDEX = "Please provide a valid food index";

    // Identity fields
    private final String name;
    private final int price;
    private final String description;
    private final String foodImageName;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final String canteen;
    private final String stallName;
    private final int stallNo;

    /** Every field must be present and not null. */
    public Food(
            String name,
            int price,
            String description,
            String foodImageName,
            int stallNo,
            String canteen,
            String stallName,
            Set<Tag> tags) {
        requireAllNonNull(name, price, description, tags);
        this.name = name;
        this.price = price;
        this.description = description;
        this.stallNo = stallNo;
        this.canteen = canteen;
        //this.foodImageName = foodImageName;
        this.foodImageName = name;
        //Current foodImageName will get back image object, cannot use
        this.stallName = stallName;
        this.tags.addAll(tags);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCanteen() {
        return canteen;
    }

    public String getStallName() {
        return stallName;
    }

    public Image getFoodImage() {
        String mImageUrl = IMAGE_FOLDER + canteen + "/" + stallName + "/" + foodImageName + ".png";
        System.out.println(mImageUrl);
        Image image = new Image(Food.class.getResourceAsStream(mImageUrl));
        return image;
    }

    public int getStallNo() {
        return stallNo;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
     * modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both food of the same name have at least one other identity field that is the
     * same. This defines a weaker notion of equality between two food.
     */
    public boolean isSameFood(Food otherCanteen) {
        if (otherCanteen == this) {
            return true;
        }

        return otherCanteen != null
                && otherCanteen.getName().equals(getName())
                && (otherCanteen.getPrice() == (getPrice()))
                && otherCanteen.getTags().equals(getTags());
    }

    /**
     * Returns true if the {@code trimmedFoodName} is a valid Food name
     */
    public static boolean isValidFood(String trimmedFoodName) {
        return Arrays.stream(FOOD)
                .anyMatch(trimmedFoodName::equalsIgnoreCase);
    }

    /**
     * Returns true if both food have the same identity and data fields. This defines a stronger
     * notion of equality between two food.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherCanteen = (Food) other;
        return otherCanteen.getName().equals(getName())
                && otherCanteen.getPrice() == (getPrice())
                && otherCanteen.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, price, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Name: ")
                .append(getName())
                .append(" Price: ")
                .append(getPrice())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
