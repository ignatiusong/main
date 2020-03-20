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
    //Possible Tags: spicy, non-spicy, rice, noodle, expensive, cheap, asian, western, halal, non-halal

    public static final String COM1_TO_DECK_DIRECTIONS =
        "1. From Com1 entrance main entrance (facing car park), turn right\n"
            + "2. Walk up the steps ";

    public static final String COM1_TO_NUSFLAVORS_DIRECTIONS =
        "1. From Com1, take shuttle D2 to Utown\n"
            + "2. Follow the path to Stephen Riady Centre\n"
            + "3. Walk straight and the canteen appears to your right";
    public static Canteen[] getSampleCanteens() {
        ArrayList<Stall> deckStalls = new ArrayList<Stall>(
                Arrays.asList(
                        new Stall(new Name("Chinese Cooked Food"), "The Deck", 1, "ChineseCookedFood.png",
                        "asian",
                        "$", 0, getTagSet("rice", "noodle", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Claypot Seafood Soup"), "The Deck", 2, "ClaypotSeafoodSoup.png",
                        "asian",
                        "$", 0, getTagSet("rice", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Indian"), "The Deck", 3, "Indian.png",
                        "asian",
                        "$", 0, getTagSet("spicy", "halal", "cheap", "asian"), new ArrayList<>()),
                        new Stall(new Name("Japanese"), "The Deck", 4, "Jap.png",
                        "asian",
                        "$$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
                        new Stall(new Name("Muslim"), "The Deck", 5, "Muslim.png",
                        "asian",
                        "$", 0, getTagSet("rice", "spicy", "halal", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Noodle"), "The Deck", 6, "Noodle.png",
                        "asian",
                        "$", 0, getTagSet("noodle", "cheap", "asian"), new ArrayList<>()),
                        new Stall(new Name("Roasted Delights"), "The Deck", 7, "RoastedDelights.png",
                        "asian",
                        "$", 0, getTagSet("rice", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Snacks and Fried Kway Teow"), "The Deck", 8, "Snacks&FKT.png",
                        "asian",
                        "$", 0, getTagSet("noodle", "cheap", "asian"), new ArrayList<>()),
                        new Stall(new Name("Yong Tau Foo and Laksa"), "The Deck", 9, "YTF&Laksa.png",
                        "asian",
                        "$", 0, getTagSet("noodle", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Vegetarian"), "The Deck", 10, "Vegetarian.png",
                        "asian",
                        "$", 0, getTagSet("rice", "cheap", "halal"), new ArrayList<>()),
                        new Stall(new Name("Western"), "The Deck", 11, "Western.png",
                        "western",
                        "$", 0, getTagSet("expensive", "western"), new ArrayList<>()),
                        new Stall(new Name("Pasta Express"), "The Deck", 13, "PastaExpress.png",
                        "western",
                        "$$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Salad Express"), "The Deck", 14, "SaladExpress.png",
                        "asian",
                        "$", 0, getTagSet("expensive"), new ArrayList<>()),
                        new Stall(new Name("Uncle Penyet"), "The Deck", 15, "UnclePenyet.png",
                        "fusion",
                        "$", 0, getTagSet("asian", "western", "rice", "spicy", "expensive"), new ArrayList<>())));
        ArrayList<Stall> flavourStalls = new ArrayList<Stall>(
                Arrays.asList (
                        new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 1, "DuckandChickenRice.png",
                                "asian",
                                "$", 0, getTagSet("rice", "asian"), new ArrayList<>()),
                        new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 5, "FiveGrainsBeeHoon.png",
                                "asian",
                                "$", 0, getTagSet("noodle", "asian"), new ArrayList<>()),
                        new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                                "Indian",
                                "$", 0, getTagSet("asian", "spicy", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Japenese and Korean"), "Nus Flavors", 5, "JapKor.png",
                                "Fusion",
                                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 3, "MalaHotPot.png",
                                "asian",
                                "$", 0, getTagSet("asian", "spicy", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 5, "MincedMeatNoodle.png",
                                "asian",
                                "$", 0, getTagSet("noodle", "asian", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 3, "MixedVegRice.png",
                                "asian",
                                "$", 0, getTagSet("rice", "cheap", "asian"), new ArrayList<>()),
                        new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 5, "TaiwanCuisine.png",
                                "asian",
                                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Western"), "Nus Flavors", 3, "Western.png",
                                "Western",
                                "$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Wok Fried"), "Nus Flavors", 5, "WokFried.png",
                                "asian",
                                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
                        new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 3, "XiaoLongBao.png",
                                "asian",
                                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>())));
        return new Canteen[]{
            new Canteen(
                new Name("Nus Flavors"),
                11,
                3200,
                "COM1",
                "utown_flavors.png",
                COM1_TO_NUSFLAVORS_DIRECTIONS,
                getTagSet("asian", "western", "muslim"), "utown.jpg", new ArrayList<>(flavourStalls)),
            new Canteen(
                new Name("The Deck"),
                1,
                1000,
                "COM1",
                "com1_deck.png",
                COM1_TO_DECK_DIRECTIONS,
                getTagSet("asian"), "deck.jpg", new ArrayList<>(deckStalls)),
        };
    }

    public static Stall[] getSampleStalls() {
        return new Stall[]{
            new Stall(new Name("Chinese Cooked Food"), "The Deck", 1, "ChineseCookedFood.png",
                "asian",
                "$", 0, getTagSet("rice", "noodle", "cheap"), new ArrayList<>()),
            new Stall(new Name("Claypot Seafood Soup"), "The Deck", 2, "ClaypotSeafoodSoup.png",
                "asian",
                "$", 0, getTagSet("rice", "cheap"), new ArrayList<>()),
            new Stall(new Name("Indian"), "The Deck", 3, "Indian.png",
                "asian",
                "$", 0, getTagSet("spicy", "halal", "cheap", "asian"), new ArrayList<>()),
            new Stall(new Name("Japanese"), "The Deck", 4, "Jap.png",
                "asian",
                "$$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
            new Stall(new Name("Muslim"), "The Deck", 5, "Muslim.png",
                "asian",
                "$", 0, getTagSet("rice", "spicy", "halal", "cheap"), new ArrayList<>()),
            new Stall(new Name("Noodle"), "The Deck", 6, "Noodle.png",
                "asian",
                "$", 0, getTagSet("noodle", "cheap", "asian"), new ArrayList<>()),
            new Stall(new Name("Roasted Delights"), "The Deck", 7, "RoastedDelights.png",
                "asian",
                "$", 0, getTagSet("rice", "cheap"), new ArrayList<>()),
            new Stall(new Name("Snacks and Fried Kway Teow"), "The Deck", 8, "Snacks&FKT.png",
                "asian",
                "$", 0, getTagSet("noodle", "cheap", "asian"), new ArrayList<>()),
            new Stall(new Name("Yong Tau Foo and Laksa"), "The Deck", 9, "YTF&Laksa.png",
                "asian",
                "$", 0, getTagSet("noodle", "expensive"), new ArrayList<>()),
            new Stall(new Name("Vegetarian"), "The Deck", 10, "Vegetarian.png",
                "asian",
                "$", 0, getTagSet("rice", "cheap", "halal"), new ArrayList<>()),
            new Stall(new Name("Western"), "The Deck", 11, "Western.png",
                "western",
                "$", 0, getTagSet("expensive", "western"), new ArrayList<>()),
            new Stall(new Name("Pasta Express"), "The Deck", 13, "PastaExpress.png",
                "western",
                "$$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
            new Stall(new Name("Salad Express"), "The Deck", 14, "SaladExpress.png",
                "asian",
                "$", 0, getTagSet("expensive"), new ArrayList<>()),
            new Stall(new Name("Uncle Penyet"), "The Deck", 15, "UnclePenyet.png",
                "fusion",
                "$", 0, getTagSet("asian", "western", "rice", "spicy", "expensive"), new ArrayList<>()),
            new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 3, "DuckandChickenRice.png",
                "asian",
                "$", 0, getTagSet("rice", "asian"), new ArrayList<>()),
            new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 5, "FiveGrainsBeeHoon.png",
                "asian",
                "$", 0, getTagSet("noodle", "asian"), new ArrayList<>()),
            new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                "Indian",
                "$", 0, getTagSet("asian", "spicy", "cheap"), new ArrayList<>()),
            new Stall(new Name("Japenese and Korean"), "Nus Flavors", 5, "JapKor.png",
                "Fusion",
                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
            new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 3, "MalaHotPot.png",
                "asian",
                "$", 0, getTagSet("asian", "spicy", "expensive"), new ArrayList<>()),
            new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 5, "MincedMeatNoodle.png",
                "asian",
                "$", 0, getTagSet("noodle", "asian", "cheap"), new ArrayList<>()),
            new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 3, "MixedVegRice.png",
                "asian",
                "$", 0, getTagSet("rice", "cheap", "asian"), new ArrayList<>()),
            new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 5, "TaiwanCuisine.png",
                "asian",
                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
            new Stall(new Name("Western"), "Nus Flavors", 3, "Western.png",
                "Western",
                "$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
            new Stall(new Name("Wok Fried"), "Nus Flavors", 5, "WokFried.png",
                "asian",
                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
            new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 3, "XiaoLongBao.png",
                "asian",
                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
        };
    }

    public static Food[] getSampleFoods() {
        return new Food[] { new Food("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                        1, "Nus Flavors", "Western", getTagSet("western", "expensive")),
                            new Food("Cai Fan", 3, "1 Meat, 2 Veg", "Cai Fan.png",
                        1, "The Deck", "Chinese Cooked Food", getTagSet("expensive")),
                            new Food("Ayam Penyet", 5, "Ayam Penyet", "Ayam Penyet.png",
                        15, "The Deck", "Uncle Penyet", getTagSet("asian", "western", "expensive")),
                            new Food("Fried Fish", 5, "Fried Fish and chips", "Fried Fish.png",
                        11, "The Deck", "Western", getTagSet("western", "expensive")),
        };
    }


    private static FavoriteFood[] getSampleFavoriteFood() {
        return new FavoriteFood[]{new FavoriteFood("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                1, "Nus Flavors", "Western", getTagSet("1")),
            new FavoriteFood("Cai Fan", 3, "1 Meat, 2 Veg", "Cai Fan.png",
                1, "The Deck", "Chinese Cooked Food", getTagSet("expensive")),
            new FavoriteFood("Ayam Penyet", 5, "Ayam Penyet", "Ayam Penyet.png",
                15, "The Deck", "Uncle Penyet", getTagSet("asian", "western", "expensive")),
            new FavoriteFood("Fried Fish", 5, "Fried Fish and chips", "Fried Fish.png",
                11, "The Deck", "Western", getTagSet("western", "expensive")),
        };
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
