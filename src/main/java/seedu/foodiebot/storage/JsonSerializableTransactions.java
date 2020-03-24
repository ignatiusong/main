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
import seedu.foodiebot.model.transaction.PurchasedFood;

/** An Immutable FoodieBot that is serializable to JSON format. */
@JsonRootName(value = "transactions")
public class JsonSerializableTransactions {
    private final List<JsonAdaptedPurchasedFood> transactions = new ArrayList<>();

    /** Constructs a {@code JsonSerializableTransactions} with the given stalls. */
    @JsonCreator
    public JsonSerializableTransactions(@JsonProperty("transactions") List<JsonAdaptedPurchasedFood> transactions) {
        this.transactions.addAll(transactions);
    }

    /**
     * Converts a given {@code ReadOnlyFoodieBot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code
     *     JsonSerializableFoodieBot}.
     */
    public JsonSerializableTransactions(ReadOnlyFoodieBot source) {
        transactions.addAll(
                source.getTransactionsList().stream()
                        .map(JsonAdaptedPurchasedFood::new)
                        .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code FoodieBot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FoodieBot toModelType() throws IllegalValueException {
        FoodieBot foodieBot = new FoodieBot();
        for (JsonAdaptedPurchasedFood jsonAdaptedPurchasedFood : transactions) {
            PurchasedFood purchasedFood = jsonAdaptedPurchasedFood.toModelType();

            foodieBot.addPurchasedFood(purchasedFood);
        }
        return foodieBot;
    }
}
