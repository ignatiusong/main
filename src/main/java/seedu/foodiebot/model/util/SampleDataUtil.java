package seedu.foodiebot.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final String COM1_TO_DECK_DIRECTIONS =
        "1. From Com1 entrance main entrance (facing car park), turn right\n"
            + "2. Walk up the steps ";

    public static final String COM1_TO_NUSFLAVORS_DIRECTIONS =
        "1. From Com1, take shuttle D2 to Utown\n"
            + "2. Follow the path to Stephen Riady Centre "
            + "3. Walk straight and the canteen appears to your right";
    public static Canteen[] getSampleCanteens() {
        return new Canteen[]{
            new Canteen(new Name("The Deck"), 2, 800, "COM1",
                "com1_deck.png",
                COM1_TO_DECK_DIRECTIONS, getTagSet("asian"), "deck.jpg"),
            new Canteen(
                new Name("Nus Flavors"),
                2,
                3200,
                "COM1",
                "utown_flavors.png",
                COM1_TO_NUSFLAVORS_DIRECTIONS,
                getTagSet("asian", "western", "muslim"), "utown.jpg")
        };
    }

    public static ReadOnlyFoodieBot getSampleFoodieBot() {
        FoodieBot sampleAb = new FoodieBot();
        for (Canteen sampleCanteen : getSampleCanteens()) {
            sampleAb.addCanteen(sampleCanteen);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }
}
