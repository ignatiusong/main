package seedu.foodiebot.testutil;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.canteen.Canteen;

/**
 * A utility class to help with building FoodieBot objects. Example usage: <br>
 * {@code FoodieBot ab = new FoodieBotBuilder().withCanteen("The Deck").build();}
 */
public class FoodieBotBuilder {

    private FoodieBot foodieBot;

    public FoodieBotBuilder() {
        foodieBot = new FoodieBot();
    }

    public FoodieBotBuilder(FoodieBot foodieBot) {
        this.foodieBot = foodieBot;
    }

    /**
     * Adds a new {@code Canteen} to the {@code FoodieBot} that we are building.
     */
    public FoodieBotBuilder withCanteen(Canteen canteen) {
        foodieBot.addCanteen(canteen);
        return this;
    }

    public FoodieBot build() {
        return foodieBot;
    }
}
