package seedu.foodiebot.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.tag.Tag;

/**
 * A utility class to help with building Stall objects.
 */
public class StallBuilder {

    public static final String DEFAULT_NAME = "Taiwanese";
    public static final String DEFAULT_CANTEEN_NAME = "The Deck";
    public static final int DEFAULT_STALL_NUMBER = 0;
    public static final String DEFAULT_CUISINE = "muslim";
    public static final String DEFAULT_STALL_IMAGE = "taiwanese.png";
    public static final String DEFAULT_OVERALL_PRICERATING = "$";
    public static final int DEFAULT_IS_FAVORITE = 0;
    public static final Tag DEFAULT_TAG = new Tag("spicy");
    private final Set<Tag> tags = new HashSet<>();


    private Name name;
    private String canteenName;
    private int stallNumber;
    private String stallImageName;
    private String cuisine;
    private String overallPriceRating;
    private int favorite;

    public StallBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.canteenName = DEFAULT_CANTEEN_NAME;
        this.stallNumber = DEFAULT_STALL_NUMBER;
        this.stallImageName = DEFAULT_STALL_IMAGE;
        this.cuisine = DEFAULT_CUISINE;
        this.overallPriceRating = DEFAULT_OVERALL_PRICERATING;
        this.favorite = DEFAULT_IS_FAVORITE;
        this.tags.add(DEFAULT_TAG);
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public StallBuilder(Stall stallToCopy) {
        name = stallToCopy.getName();
        this.canteenName = stallToCopy.getCanteenName();
        this.stallNumber = stallToCopy.getStallNumber();
        this.stallImageName = stallToCopy.getStallImageName();
        this.cuisine = stallToCopy.getCuisine();
        this.overallPriceRating = stallToCopy.getOverallPriceRating();
        this.favorite = stallToCopy.getFavorite();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public StallBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code canteenName} of the {@code Stall} that we are building.
     */
    public StallBuilder withCanteenName(String canteenName) {
        this.canteenName = canteenName;
        return this;
    }


    /**
     * Sets the {@code stallNumber} of the {@code Stall} that we are building.
     */
    public StallBuilder withStallNumber(int stallNumber) {
        this.stallNumber = stallNumber;
        return this;
    }


    /**
     * Sets the {@code stallImageName} of the {@code Stall} that we are building.
     */
    public StallBuilder withStallImageName(String stallImageName) {
        this.stallImageName = stallImageName;
        return this;
    }


    /**
     * Sets the {@code cuisine} of the {@code Stall} that we are building.
     */
    public StallBuilder withCuisine(String cuisine) {
        this.cuisine = cuisine;
        return this;
    }

    /**
     * Sets the {@code overallPriceRating} of the {@code Stall} that we are building.
     */
    public StallBuilder withOverallPriceRating(String overallPriceRating) {
        this.overallPriceRating = overallPriceRating;
        return this;
    }

    /**
     * Sets the {@code favorite} of the {@code Stall} that we are building.
     */
    public StallBuilder withFavorite(int favorite) {
        this.favorite = favorite;
        return this;
    }

    /**
     * Creates the stall object.
     */
    public Stall build() {
        return new Stall(name, canteenName, stallNumber, stallImageName, cuisine,
            overallPriceRating, favorite, tags, new ArrayList<>());
    }
}
