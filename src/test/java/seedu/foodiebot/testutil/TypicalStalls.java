package seedu.foodiebot.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.canteen.Stall;

/**
 * A utility class containing a list of {@code Stall} objects to be used in tests.
 */
public class TypicalStalls {
    // Manually added
    public static final Stall MUSLIM = new StallBuilder().withName("Muslim").withCanteenName("The Deck")
        .withStallNumber(5).withStallImageName("taiwanese.png").withCuisine("muslim").withOverallPriceRating("$")
        .withFavorite(0)
        .build();


    public static final Stall TAIWANESE = new StallBuilder().withName("Taiwanese").withCanteenName("Nus Flavors")
        .withStallNumber(5).withStallImageName("yongtaufoo.png").withCuisine("asian").withOverallPriceRating("$")
        .withFavorite(1)
        .build();


    public static final String KEYWORD_MATCHING_DECK = "Deck"; // A keyword that matches DECK

    private TypicalStalls() {
    } // prevents instantiation

    /**
     * Returns an {@code FoodieBot} with all the typical canteens.
     */
    public static FoodieBot getTypicalFoodieBot() {
        FoodieBot ab = new FoodieBot();
        for (Stall stall : getTypicalStalls()) {
            ab.addStall(stall);
        }
        return ab;
    }

    public static List<Stall> getTypicalStalls() {
        return new ArrayList<>(Arrays.asList(MUSLIM, TAIWANESE));
    }
}
