package seedu.foodiebot.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalCanteens {
    public static final String COM1_TO_DECK_DIRECTIONS =
        "1. From Com1 entrance main entrance (facing car park), turn right\n"
            + "2. Walk up the steps ";

    public static final String COM1_TO_NUSFLAVORS_DIRECTIONS =
        "1. From Com1, take shuttle D2 to Utown\n"
            + "2. Follow the path to Stephen Riady Centre "
            + "3. Walk straight and the canteen appears to your right";
    // Manually added
    public static final Canteen DECK =
        new CanteenBuilder().withName("The Deck")
            .withDistance(800).withNumberOfStalls(2).withNearestBlock("COM1")
            .withImage("deck.jpg")
            .withDirectionsImage("com1_deck.png")
            .withDirections(COM1_TO_DECK_DIRECTIONS)
            .withTags("asian").build();
    public static final Canteen NUSFLAVORS =
        new CanteenBuilder().withName("Nus Flavors").withNumberOfStalls(2)
            .withDistance(3200).withNearestBlock("COM1")
            .withImage("utown.jpg")
            .withDirectionsImage("utown_flavors.png")
            .withDirections(COM1_TO_NUSFLAVORS_DIRECTIONS)
            .withTags("muslim", "asian", "western").build();


    public static final String KEYWORD_MATCHING_DECK = "Deck"; // A keyword that matches DECK

    private TypicalCanteens() {
    } // prevents instantiation

    /**
     * Returns an {@code FoodieBot} with all the typical canteens.
     */
    public static FoodieBot getTypicalFoodieBot() {
        FoodieBot ab = new FoodieBot();
        for (Canteen canteen : getTypicalCanteens()) {
            ab.addCanteen(canteen);
        }

        for (Food food : SampleDataUtil.getSampleFoods()) {
            ab.addFood(food);
        }
        return ab;
    }

    public static List<Canteen> getTypicalCanteens() {
        return new ArrayList<>(Arrays.asList(DECK, NUSFLAVORS));
    }
}
