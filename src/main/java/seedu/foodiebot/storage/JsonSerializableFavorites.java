package seedu.foodiebot.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.foodiebot.commons.exceptions.IllegalValueException;
import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.food.Food;

/** An Immutable AddressBook that is serializable to JSON format. */
@JsonRootName(value = "favoriteList")
class JsonSerializableFavorites {

    public static final String MESSAGE_DUPLICATE_FAVORITE =
            "Food list contains duplicate favorites(s).";

    private final List<JsonAdaptedFood> favoriteList = new ArrayList<>();

    /** Constructs a {@code JsonSerializableAddressBook} with the given stalls. */
    @JsonCreator
    public JsonSerializableFavorites(@JsonProperty("favoriteList") List<JsonAdaptedFood> foods) {
        this.favoriteList.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code
     *     JsonSerializableAddressBook}.
     */
    public JsonSerializableFavorites(ReadOnlyFoodieBot source) {
        favoriteList.addAll(
                source.getFavoriteList().stream()
                        .map(JsonAdaptedFood::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodieBot toModelType() throws IllegalValueException {
        FoodieBot foodieBot = new FoodieBot();
        for (JsonAdaptedFood jsonAdaptedFood : favoriteList) {
            Food food = jsonAdaptedFood.toModelType();
            if (foodieBot.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FAVORITE);
            }

            foodieBot.addFavoriteFood(food);
        }
        return foodieBot;
    }
}
