package seedu.foodiebot.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.tag.Tag;
import seedu.foodiebot.model.util.SampleDataUtil;

/**
 * A utility class to help with building Canteen objects.
 */
public class CanteenBuilder {

    public static final String DEFAULT_NAME = "The Deck";
    public static final int DEFAULT_NUMBEROFSTALLS = 0;
    public static final int DEFAULT_DISTANCE = 0;
    public static final String DEFAULT_BLOCKNAME = "Kent Ridge MRT";
    public static final String DEFAULT_IMAGENAME = "deck.jpg";
    public static final String DEFAULT_DIRECTIONSIMAGENAME = "com1_deck.png";
    public static final String DEFAULT_DIRECTIONS = "This is a sample text";

    private Name name;
    private int numberOfStalls;
    private int distance;
    private String blockName;
    private String canteenImageName;
    private String directionsImageName;
    private String directions;
    private Set<Tag> tags;

    public CanteenBuilder() {
        name = new Name(DEFAULT_NAME);
        numberOfStalls = DEFAULT_NUMBEROFSTALLS;
        distance = DEFAULT_DISTANCE;
        blockName = DEFAULT_BLOCKNAME;
        canteenImageName = DEFAULT_IMAGENAME;
        directionsImageName = DEFAULT_DIRECTIONSIMAGENAME;
        directions = DEFAULT_DIRECTIONS;
        tags = new HashSet<>();
    }

    /**
     * Initializes the CanteenBuilder with the data of {@code canteenToCopy}.
     */
    public CanteenBuilder(Canteen canteenToCopy) {
        name = canteenToCopy.getName();
        numberOfStalls = canteenToCopy.getNumberOfStalls();
        distance = canteenToCopy.getDistance();
        blockName = canteenToCopy.getBlockName();
        directionsImageName = canteenToCopy.getDirectionImageName();
        canteenImageName = canteenToCopy.getCanteenImageName();
        tags = new HashSet<>(canteenToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code distance} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withDistance(int distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Sets the {@code blockName} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withNearestBlock(String blockName) {
        this.blockName = blockName;
        return this;
    }


    /**
     * Sets the {@code imageName} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withImage(String imageName) {
        this.canteenImageName = imageName;
        return this;
    }

    /**
     * Sets the {@code directions} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withDirections(String directions) {
        this.directions = directions;
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Canteen} that we are
     * building.
     */
    public CanteenBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code numberOfStalls} of the {@code Canteen} that we are building.
     */
    public CanteenBuilder withNumberOfStalls(int numberOfStalls) {
        this.numberOfStalls = numberOfStalls;
        return this;
    }

    /** Creates the canteen object. */
    public Canteen build() {
        return new Canteen(name, numberOfStalls, distance, blockName, directionsImageName,
                directions, tags, canteenImageName);
    }
}
