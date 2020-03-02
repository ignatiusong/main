package seedu.foodiebot.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.tag.Tag;



/** Jackson-friendly version of {@link Canteen}. */
class JsonAdaptedCanteen {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Canteen's %s field is missing!";

    private final String name;
    private final int numberOfStalls;
    private final String nearestBlockName;
    private final int distance;
    private final String directionsImageName;
    private final String canteenImageName;
    private final String directionsText;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /** Constructs a {@code JsonAdaptedCanteen} with the given person details. */
    @JsonCreator
    public JsonAdaptedCanteen(
            @JsonProperty("name") String name,
            @JsonProperty("distance") String distance,
            @JsonProperty("numberOfStalls") String numberOfStalls,
            @JsonProperty("nearestBlockName") String nearestBlockName,
            @JsonProperty("directionsImageName") String directionsImageName,
            @JsonProperty("canteenImageName") String canteenImageName,
            @JsonProperty("directionsText") String directionsText,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.numberOfStalls = Integer.parseInt(numberOfStalls);
        this.nearestBlockName = nearestBlockName;
        this.distance = Integer.parseInt(distance);

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.directionsImageName = directionsImageName;
        this.canteenImageName = canteenImageName;
        this.directionsText = directionsText;
    }

    /** Converts a given {@code Canteen} into this class for Jackson use. */
    public JsonAdaptedCanteen(Canteen source) {
        name = source.getName().fullName;
        numberOfStalls = source.getNumberOfStalls();
        nearestBlockName = source.getBlockName();
        distance = source.getDistance();
        tagged.addAll(
                source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        directionsImageName = source.getDirectionImageName();
        canteenImageName = source.getCanteenImageName();
        directionsText = source.getDirectionsText();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Canteen} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *     person.
     */
    public Canteen toModelType() throws IllegalValueException {
        final List<Tag> canteenTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            canteenTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final int modelNumberOfStalls = numberOfStalls;

        final String modelBlockName = nearestBlockName;

        if (modelBlockName == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, "Block"));
        }

        final Set<Tag> modelTags = new HashSet<>(canteenTags);
        return new Canteen(modelName, modelNumberOfStalls, distance, modelBlockName, directionsImageName,
                directionsText, modelTags, canteenImageName);
    }
}
