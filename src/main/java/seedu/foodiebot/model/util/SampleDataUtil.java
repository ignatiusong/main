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
                        new Stall(new Name("Pasta Express"), "The Deck", 12, "PastaExpress.png",
                        "western",
                        "$$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Salad Express"), "The Deck", 13, "SaladExpress.png",
                        "asian",
                        "$", 0, getTagSet("expensive"), new ArrayList<>()),
                        new Stall(new Name("Uncle Penyet"), "The Deck", 14, "UnclePenyet.png",
                        "fusion",
                        "$", 0, getTagSet("asian", "western", "rice", "spicy", "expensive"), new ArrayList<>())));
        ArrayList<Stall> flavourStalls = new ArrayList<Stall>(
                Arrays.asList (
                        new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 1, "DuckandChickenRice.png",
                                "asian",
                                "$", 0, getTagSet("rice", "asian"), new ArrayList<>()),
                        new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 2, "FiveGrainsBeeHoon.png",
                                "asian",
                                "$", 0, getTagSet("noodle", "asian"), new ArrayList<>()),
                        new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                                "Indian",
                                "$", 0, getTagSet("asian", "spicy", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Japanese and Korean"), "Nus Flavors", 4, "JapKor.png",
                                "Fusion",
                                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 5, "MalaHotPot.png",
                                "asian",
                                "$", 0, getTagSet("asian", "spicy", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 6, "MincedMeatNoodle.png",
                                "asian",
                                "$", 0, getTagSet("noodle", "asian", "cheap"), new ArrayList<>()),
                        new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 7, "MixedVegRice.png",
                                "asian",
                                "$", 0, getTagSet("rice", "cheap", "asian"), new ArrayList<>()),
                        new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 8, "TaiwanCuisine.png",
                                "asian",
                                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Western"), "Nus Flavors", 9, "Western.png",
                                "Western",
                                "$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
                        new Stall(new Name("Wok Fried"), "Nus Flavors", 10, "WokFried.png",
                                "asian",
                                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
                        new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 11, "XiaoLongBao.png",
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
                14,
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
            new Stall(new Name("Pasta Express"), "The Deck", 12, "PastaExpress.png",
                "western",
                "$$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
            new Stall(new Name("Salad Express"), "The Deck", 13, "SaladExpress.png",
                "asian",
                "$", 0, getTagSet("expensive"), new ArrayList<>()),
            new Stall(new Name("Uncle Penyet"), "The Deck", 14, "UnclePenyet.png",
                "fusion",
                "$", 0, getTagSet("asian", "western", "rice", "spicy", "expensive"), new ArrayList<>()),
            new Stall(new Name("Duck and Chicken Rice"), "Nus Flavors", 1, "DuckandChickenRice.png",
                "asian",
                "$", 0, getTagSet("rice", "asian"), new ArrayList<>()),
            new Stall(new Name("Five Grains Bee Hoon"), "Nus Flavors", 2, "FiveGrainsBeeHoon.png",
                "asian",
                "$", 0, getTagSet("noodle", "asian"), new ArrayList<>()),
            new Stall(new Name("Indian Cuisine"), "Nus Flavors", 3, "Indian Cuisine.png",
                "Indian",
                "$", 0, getTagSet("asian", "spicy", "cheap"), new ArrayList<>()),
            new Stall(new Name("Japanese and Korean"), "Nus Flavors", 4, "JapKor.png",
                "Fusion",
                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
            new Stall(new Name("Mala Hot Pot"), "Nus Flavors", 5, "MalaHotPot.png",
                "asian",
                "$", 0, getTagSet("asian", "spicy", "expensive"), new ArrayList<>()),
            new Stall(new Name("Minced Meat Noodle"), "Nus Flavors", 6, "MincedMeatNoodle.png",
                "asian",
                "$", 0, getTagSet("noodle", "asian", "cheap"), new ArrayList<>()),
            new Stall(new Name("Mixed Veg Rice"), "Nus Flavors", 7, "MixedVegRice.png",
                "asian",
                "$", 0, getTagSet("rice", "cheap", "asian"), new ArrayList<>()),
            new Stall(new Name("Taiwan Cuisine"), "Nus Flavors", 8, "TaiwanCuisine.png",
                "asian",
                "$", 0, getTagSet("asian", "expensive"), new ArrayList<>()),
            new Stall(new Name("Western"), "Nus Flavors", 9, "Western.png",
                "Western",
                "$", 0, getTagSet("western", "expensive"), new ArrayList<>()),
            new Stall(new Name("Wok Fried"), "Nus Flavors", 10, "WokFried.png",
                "asian",
                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
            new Stall(new Name("Xiao Long Bao"), "Nus Flavors", 11, "XiaoLongBao.png",
                "asian",
                "$", 0, getTagSet("expensive", "asian"), new ArrayList<>()),
        };
    }

    public static Food[] getSampleFoods() {
        return new Food[] { new Food("Roasted Chicken Rice", 4, "Roasted Meat and Chicken Rice",
                        "Roasted Chicken Rice.png", 1, "Nus Flavors",
                        "Duck and Chicken Rice", getTagSet("asian", "cheap", "rice")),
                            new Food("Fish Bee Hoon", 4, "Been Hoon with Fish", "Fish Bee Hoon.png",
                        2, "Nus Flavors", "Five Grains Bee Hoon", getTagSet("asian", "cheap", "noodle")),
                            new Food("Roti Prata Set", 3, "2 Pieces and Curry", "Roti Prata Set.png",
                        3, "Nus Flavors", "Indian Cuisine", getTagSet("cheap", "indian")),
                            new Food("Beef Rice Set", 5, "Fried Korean Beef with Rice", "Beef Rice Set.png",
                        4, "Nus Flavors", "Japanese and Korean", getTagSet("korean", "expensive")),
                            new Food("Mala", 10, "Assorted Meat and Veg with Mala Sauce", "Mala.png",
                        5, "Nus Flavors", "Mala Hot Pot", getTagSet("asian", "expensive", "spicy")),
                            new Food("Fried Meatball Noodle", 5, "Fried Meatball with Minced Meat Noodles",
                        "Fried Meatball Noodle.png", 6, "Nus Flavors",
                        "Minced Meat Noodle", getTagSet("asian", "noodle")),
                            new Food("Cai Fan Set", 3, "2 Meat 1 Veg", "Cai Fan Set.png",
                        7, "Nus Flavors", "Mixed Veg Rice", getTagSet("asian", "cheap", "rice")),
                            new Food("Popcorn Chicken with Veg Set", 6, "Popcorn Chicken, vegetable and rice",
                        "Popcorn Chicken with Veg Set.png", 8, "Nus Flavors",
                        "Taiwan Cuisine", getTagSet("asian", "expensive")),
                            new Food("Combo Set", 6, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
                        9, "Nus Flavors", "Western", getTagSet("western", "expensive")),
                            new Food("Fried Rice", 4, "Local Fried Rice", "Fried Rice.png",
                        10, "Nus Flavors", "Wok Fried", getTagSet("asian", "expensive", "rice")),
                            new Food("Xiao Long Bao", 5, "7 pieces", "Xiao Long Bao.png",
                        11, "Nus Flavors", "Xiao Long Bao", getTagSet("asian", "expensive")),
                            new Food("Cai Fan", 3, "1 Meat, 2 Veg", "Cai Fan Set.png",
                        1, "The Deck", "Chinese Cooked Food", getTagSet("expensive")),
                            new Food("Assorted Claypot Chicken with Rice", 4, "Black sauce chicken wtih rice",
                        "Assorted Claypot Chicken with Rice.png", 2, "The Deck",
                        "Claypot Seafood Soup", getTagSet("asian", "cheap", "rice")),
                            new Food("Roti Prata", 3, "2 pieces and curry", "Roti Prata.png",
                        3, "The Deck", "Indian", getTagSet("indian", "spicy", "cheap")),
                            new Food("Katsudon Rice Set", 5, "Chicken Cutlet with rice", "Katsudon Rice Set.png",
                        4, "The Deck", "Japanese", getTagSet("japanese", "expensive", "rice")),
                            new Food("Beef Rendang", 5, "Nasi Goodness", "Beef Rendang.png",
                        5, "The Deck", "Muslim", getTagSet("asian", "expensive", "spicy")),
                            new Food("Mini Hot Pot Noodle", 4, "Minced Meat Noodle with Soup",
                        "Mini Hot Pot Noodle.png", 6, "The Deck",
                        "Noodle", getTagSet("asian", "cheap", "noodles")),
                            new Food("Chicken Rice Set", 4, "Rice, Chicken, Vegetable and Soup", "Chicken Rice Set.png",
                        7, "The Deck", "Roasted Delights", getTagSet("asian", "cheap")),
                            new Food("Fried Kway Teow", 4, "Fried Kway Teow with fried delights", "Fried Kway Teow.png",
                        8, "The Deck", "Snacks and Fried Kway Teow", getTagSet("asian", "cheap")),
                            new Food("Laksa Yong Tau Foo", 4, "Spicy and Delicious", "Laksa Yong Tau Foo.png",
                        9, "The Deck", "Yong Tau Foo and Laksa", getTagSet("asian", "expensive", "spicy")),
                            new Food("Vegetarian Set", 4, "Vegetable with rice", "Vegetarian Set.png",
                        10, "The Deck", "Vegetarian", getTagSet("asian", "cheap", "vegetarian")),
                            new Food("Fried Fish", 5, "Fried Fish and chips", "Fried Fish.png",
                        11, "The Deck", "Western", getTagSet("western", "expensive")),
                            new Food("Carbonara", 6, "Pasta with Cream Sauce", "Carbonara.png",
                        12, "The Deck", "Pasta Express", getTagSet("western", "expensive")),
                            new Food("Assorted Salads", 6, "Variety of salads", "Assorted Salads.png",
                        13, "The Deck", "Salad Express", getTagSet("vegetarian", "expensive")),
                            new Food("Ayam Penyet", 5, "Ayam Penyet", "Ayam Penyet.png",
                        14, "The Deck", "Uncle Penyet", getTagSet("asian", "western", "expensive")),
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
        return sampleFb;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }
}
