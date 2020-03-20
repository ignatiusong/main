package seedu.foodiebot.storage;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.tag.Tag;


/** Jackson-friendly version of {@link Stall}. */
class JsonAdaptedStall {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stall's %s field is missing!";

    private final String name;
    private final String canteenName;
    private final int stallNumber;
    private final String stallImageName;
    private final String cuisine;
    private final String overallPriceRating;
    private final int favorite;

    private final Set<JsonAdaptedTag> tags = new HashSet<>();

    /** Constructs a {@code JsonAdaptedStall} with the given person details. */
    @JsonCreator
    public JsonAdaptedStall(
            @JsonProperty("name") String name,
            @JsonProperty("canteenName") String canteenName,
            @JsonProperty("stallNumber") String stallNumber,
            @JsonProperty("stallImageName") String stallImageName,
            @JsonProperty("cuisine") String cuisine,
            @JsonProperty("overallPriceRating") String overallPriceRating,
            @JsonProperty("favorite") String favorite,
            @JsonProperty("tagged") Set<JsonAdaptedTag> tags) {
        this.name = name;
        this.stallNumber = Integer.parseInt(stallNumber);
        this.canteenName = canteenName;
        this.stallImageName = stallImageName;
        this.cuisine = cuisine;
        this.overallPriceRating = overallPriceRating;
        this.favorite = Integer.parseInt(favorite);
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /** Converts a given {@code Stall} into this class for Jackson use. */
    public JsonAdaptedStall(Stall source) {
        name = source.getName().fullName;
        stallNumber = source.getStallNumber();
        this.canteenName = source.getCanteenName();
        this.stallImageName = source.getStallImageName();
        this.cuisine = source.getCuisine();
        this.overallPriceRating = source.getOverallPriceRating();
        this.favorite = source.getFavorite();
        tags.addAll(
                source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Stall} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *     person.
     */
    public Stall toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        //if (!Name.isValidName(name)) {
        //    throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        //}
        final Name modelName = new Name(name);

        final String modelCanteenName = canteenName;

        final Set<Tag> tags = new HashSet<>();

        return new Stall(modelName, modelCanteenName, stallNumber, stallImageName, cuisine,
                overallPriceRating, favorite, tags, new ArrayList<>());
    }
}
