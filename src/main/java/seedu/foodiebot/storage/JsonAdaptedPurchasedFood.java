package seedu.foodiebot.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.tag.Tag;
import seedu.foodiebot.model.transaction.PurchasedFood;

/** Jackson-friendly version of {@link PurchasedFood}. */
public class JsonAdaptedPurchasedFood {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Food's %s field is missing!";

    //Identity fields
    private final String name;
    private final int price;
    private final String description;
    private final String foodImageName;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final String canteen;
    private final String stallName;
    private final int stallNo;

    // Transaction fields
    private final LocalDate dateAdded;
    private final LocalTime timeAdded;
    private final String rating;
    private final String review;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /** Constructs a {@code JsonAdaptedPurchasedFood} with the given person details. */
    @JsonCreator
    public JsonAdaptedPurchasedFood(
            @JsonProperty("name") String name,
            @JsonProperty("price") String price,
            @JsonProperty("description") String description,
            @JsonProperty("foodImageName") String foodImageName,
            @JsonProperty("stallNo") String stallNo,
            @JsonProperty("canteen") String canteen,
            @JsonProperty("stallName") String stallName,
            @JsonProperty("dateAdded") String dateAdded,
            @JsonProperty("timeAdded") String timeAdded,
            @JsonProperty("rating") String rating,
            @JsonProperty("review") String review) {
        this.name = name;
        this.price = Integer.parseInt(price);
        this.description = description;
        this.foodImageName = foodImageName;
        this.stallNo = Integer.parseInt(stallNo);
        this.canteen = canteen;
        this.stallName = stallName;
        this.dateAdded = LocalDate.parse(dateAdded);
        this.timeAdded = LocalTime.parse(timeAdded);
        this.rating = rating;
        this.review = review;
    }

    /** Converts a given {@code PurchasedFood} into this class for Jackson use. */
    public JsonAdaptedPurchasedFood(PurchasedFood source) {
        name = source.getName();
        this.price = source.getPrice();
        this.description = source.getDescription();
        this.foodImageName = source.getFoodImage().toString();
        this.stallNo = source.getStallNo();
        this.canteen = source.getCanteen();
        this.stallName = source.getStallName();
        this.dateAdded = source.getDateAdded();
        this.timeAdded = source.getTimeAdded();
        this.rating = source.getRating().toString();
        this.review = source.getReview().toString();
    }

    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code PurchasedFood} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted
     *     person.
     */
    public PurchasedFood toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final String modelName = name;
        final String modelStallName = stallName;

        Rating pfRating = rating.equals(Rating.NO_RATING)
                ? new Rating()
                : new Rating(Integer.parseInt(rating));

        Review pfReview = review.equals(Rating.NO_RATING)
                ? new Review()
                : new Review(review);


        return new PurchasedFood(modelName, price, description, foodImageName, stallNo,
                canteen, modelStallName, getTagSet("1"),
                dateAdded, timeAdded, pfRating, pfReview);
    }


}
