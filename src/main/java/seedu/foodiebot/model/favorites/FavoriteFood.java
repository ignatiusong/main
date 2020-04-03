package seedu.foodiebot.model.favorites;

import java.util.Set;

import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.tag.Tag;

/** .*/
public class FavoriteFood extends Food {
    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param price
     * @param description
     * @param foodImageName
     * @param stallNo
     * @param canteen
     * @param stallName
     * @param tags
     */
    public FavoriteFood(String name, int price, String description, String foodImageName,
            int stallNo, String canteen, String stallName, Set<Tag> tags) {
        super(name, price, description, foodImageName, stallNo, canteen, stallName, tags); }
}
