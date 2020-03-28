package seedu.foodiebot.model.canteen;

import static seedu.foodiebot.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import javafx.scene.image.Image;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.tag.Tag;

/**
 * Represents a Stall in FoodieBot. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Stall {
    public static final String[] STALLS = new String[]{
        "Duck and Chicken Rice", "Five Grains Bee Hoon", "Indian Cuisine",
        "Japenese Korean", "Mala Hot Pot", "Minced Meat Noodle", "Mixed Veg Rice",
        "Taiwan Cuisine", "Thai Street Food", "Western", "Wok Fried", "Xiao Long Bao",
        "Yong Tau Foo", "Chinese Cooked Food", "Claypot Seafood Soup", "Indian", "Japanese",
        "Muslim", "Noodle", "Pasta Express", "Roasted Delights", "Salad Express", "Snacks & Fried Kway Teow",
        "Uncle Penyet", "Vegetarian", "Western", "Yong Tau Foo & Laksa"};
    public static final String IMAGE_FOLDER = "/images/canteen/";
    public static final String MESSAGE_CONSTRAINTS = "Stall not found";
    private static final Logger logger = LogsCenter.getLogger(Stall.class);
    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    // Identity fields
    private final Name name;
    private final String canteenName;
    private final int stallNumber;
    private final String stallImageName;
    private final String cuisine;
    private final String overallPriceRating;
    private final int favorite;
    private final List<Food> foodMenu;

    /**
     * Every field must be present and not null.
     */
    public Stall(
        Name name, String canteenName, int stallNumber, String stallImageName,
        String cuisine, String overallPriceRating, int favorite, Set<Tag> tags, List<Food> foodMenu) {
        requireAllNonNull(name, canteenName, stallNumber, stallImageName, cuisine, overallPriceRating, favorite);
        this.name = name;
        this.canteenName = canteenName;
        this.stallNumber = stallNumber;
        this.stallImageName = stallImageName;
        this.cuisine = cuisine;
        this.overallPriceRating = overallPriceRating;
        this.favorite = favorite;
        this.foodMenu = foodMenu;
        this.tags.addAll(tags);
    }

    /**
     * Returns true if the {@code trimmedStallName} is a valid stall name
     */
    public static boolean isValidStall(String trimmedStallName) {
        return Arrays.stream(STALLS)
            .anyMatch(trimmedStallName::equalsIgnoreCase);
    }

    public Name getName() {
        return name;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public int getStallNumber() {
        return stallNumber;
    }

    public String getStallImageName() {
        return stallImageName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getOverallPriceRating() {
        return overallPriceRating;
    }

    /**
     * Retrieves the images within the folder with the canteen name.
     * e.g. Stall in The Deck with appear in The Deck folder
     **/
    public Image getStallImage() {
        String mImageUrl = IMAGE_FOLDER + canteenName + "/" + stallImageName;
        Image image = new Image(Stall.class.getResourceAsStream((mImageUrl)));
        return image;
    }

    public int getFavorite() {
        return favorite;
    }

    public List<Food> getFoodMenu() {
        return foodMenu;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is
     * the same. This defines a weaker notion of equality between two persons.
     */
    public boolean isSameStall(Stall otherStall) {
        if (otherStall == this) {
            return true;
        }

        return otherStall != null
            && otherStall.getName().equals(getName())
            && (otherStall.getCanteenName().equals(getCanteenName())
            || otherStall.getStallNumber() == (getStallNumber()));
    }

    /**
     * Returns true if both stalls have the same identity and data fields. This defines a stronger
     * notion of equality between two stalls.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Stall)) {
            return false;
        }

        Stall otherStall = (Stall) other;
        return otherStall.getName().equals(getName())
            && otherStall.getCanteenName().equals(getCanteenName())
            && otherStall.getStallNumber() == (getStallNumber())
            && otherStall.getCuisine().equals(getCuisine())
            && otherStall.getOverallPriceRating().equals(getOverallPriceRating());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, canteenName, stallNumber, cuisine);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
            .append(getName())
            .append(" CanteenName: ")
            .append(getCanteenName())
            .append(" StallNumber: ")
            .append(getStallNumber())
            .append(" StallImageName: ")
            .append(getStallImageName())
            .append(" Cuisine: ")
            .append(getCuisine())
            .append(" OverallPriceRating: ")
            .append(getOverallPriceRating())
            .append(" Favorite: ")
            .append(getFavorite());
        return builder.toString();
    }
}
