package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ModelManager;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.tag.Tag;
import seedu.foodiebot.model.util.SampleDataUtil;


class FilterCommandTest {

    private Model model;
    private Model expectedModel;


    @BeforeEach
    public void setUp() {
        FoodieBot bot = new FoodieBot();
        List<Food> foodList = Stream.of(SampleDataUtil.getSampleFoods())
            .filter(f -> f.getTags().contains(new Tag("asian"))).collect(Collectors.toList());
        model = new ModelManager(bot, new UserPrefs());
        bot.setFood(foodList);
        expectedModel = new ModelManager(bot, new UserPrefs());
    }

    @Test
    void execute_viewFavorite_success() {
        assertCommandSuccess(new FilterCommand("asian"), FilterCommand.COMMAND_WORD, model,
            FilterCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
