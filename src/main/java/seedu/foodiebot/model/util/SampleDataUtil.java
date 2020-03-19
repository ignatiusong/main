package seedu.foodiebot.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.favorites.FavoriteFood;
import seedu.foodiebot.model.food.Food;
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
        ArrayList<Stall> deckStalls = new ArrayList<Stall>(
                Arrays.asList(
                        new Stall(new Name("Taiwanese"), "The Deck", 3, "muslim.png",
                        "asian",
                        "$", 0, new ArrayList<>())));
        ArrayList<Stall> flavourStalls = new ArrayList<Stall>(
                Arrays.asList (
                        new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 3, "DuckandChickenRice.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 5, "FiveGrainsBeeHoon.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                                "Indian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Japenese and Korean"), "Nus Flavors", 5, "JapKor.png",
                                "Fusion",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 3, "MalaHotPot.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 5, "MincedMeatNoodle.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 3, "MixedVegRice.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 5, "TaiwanCuisine.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Western"), "Nus Flavors", 3, "Western.png",
                                "Western",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Wok Fried"), "Nus Flavors", 5, "WokFried.png",
                                "asian",
                                "$", 0, new ArrayList<>()),
                        new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 3, "XiaoLongBao.png",
                                "asian",
                                "$", 0, new ArrayList<>())));
        return new Canteen[]{
            new Canteen(
                new Name("Nus Flavors"),
                2,
                3200,
                "COM1",
                "utown_flavors.png",
                COM1_TO_NUSFLAVORS_DIRECTIONS,
                getTagSet("asian", "western", "muslim"), "utown.jpg", new ArrayList<>(flavourStalls)),
            new Canteen(
                new Name("The Deck"),
                3,
                1000,
                "COM1,",
                "com1_deck.png",
                COM1_TO_DECK_DIRECTIONS,
                getTagSet("asian"), "deck.jpg", new ArrayList<>(deckStalls)),
        };
    }

    public static Stall[] getSampleStalls() {
        return new Stall[]{
            new Stall(new Name("Taiwanese"), "The Deck", 3, "muslim.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 3, "DuckandChickenRice.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 5, "FiveGrainsBeeHoon.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                "Indian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Japenese and Korean"), "Nus Flavors", 5, "JapKor.png",
                "Fusion",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 3, "MalaHotPot.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 5, "MincedMeatNoodle.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 3, "MixedVegRice.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 5, "TaiwanCuisine.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Western"), "Nus Flavors", 3, "Western.png",
                "Western",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Wok Fried"), "Nus Flavors", 5, "WokFried.png",
                "asian",
                "$", 0, new ArrayList<>()),
            new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 3, "XiaoLongBao.png",
                "asian",
                "$", 0, new ArrayList<>()),
        };
    }

    public static Food[] getSampleFoods() {
        return new Food[] { new Food("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "ComboSet.png",
                        1, "Nus Flavors", "Western", getTagSet("1"))};
    }


    private static FavoriteFood[] getSampleFavoriteFood() {
        return new FavoriteFood[] { new FavoriteFood("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "ComboSet.png",
                1, "Nus Flavors", "Western", getTagSet("1"))};
    }

    public static ReadOnlyFoodieBot getSampleFoodieBot() {
        FoodieBot sampleFb = new FoodieBot();
        for (Canteen sampleCanteen : getSampleCanteens()) {
            sampleFb.addCanteen(sampleCanteen);
        }

        for (Stall sampleStall : getSampleStalls()) {
            sampleFb.addStall(sampleStall);
        }

        for (Food sampleFood : getSampleFoods()) {
            sampleFb.addFood(sampleFood);
        }

        for (FavoriteFood sampleFood: getSampleFavoriteFood()) {
            sampleFb.addFavoriteFood(sampleFood);
        }
        return sampleFb;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }
}
