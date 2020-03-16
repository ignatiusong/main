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

/** An Immutable FoodieBot that is serializable to JSON format. */
@JsonRootName(value = "foods")
class JsonSerializableFood {

    public static final String MESSAGE_DUPLICATE_FOOD =
            "Food list contains duplicate food(s).";

    private final List<JsonAdaptedFood> foods = new ArrayList<>();

    /** Constructs a {@code JsonSerializableFoodieBot} with the given stalls. */
    @JsonCreator
    public JsonSerializableFood(@JsonProperty("foods") List<JsonAdaptedFood> foods) {
        this.foods.addAll(foods);
    }

    /**
     * Converts a given {@code ReadOnlyFoodieBot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code
     *     JsonSerializableFoodieBot}.
     */
    public JsonSerializableFood(ReadOnlyFoodieBot source) {
        foods.addAll(
                source.getFoodList().stream()
                        .map(JsonAdaptedFood::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code FoodieBot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodieBot toModelType() throws IllegalValueException {
        FoodieBot foodieBot = new FoodieBot();
        for (JsonAdaptedFood jsonAdaptedFood : foods) {
            Food food = jsonAdaptedFood.toModelType();
            if (foodieBot.hasFood(food)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FOOD);
            }

            foodieBot.addFood(food);
        }
        return foodieBot;
    }
}
