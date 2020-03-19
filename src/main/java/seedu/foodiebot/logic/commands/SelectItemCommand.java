package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.commons.util.JsonUtil;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.UserPrefs;
import seedu.foodiebot.model.budget.Budget;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.storage.JsonAdaptedBudget;

/** Select the current list view item. */
public class SelectItemCommand extends Command {
    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
            + "Parameters: "
            + "FOOD_INDEX \n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + "1 ";;
    public static final String MESSAGE_SUCCESS = "You have selected: %s, this costs: $%.2f\n";
    public static final String MESSAGE_SUCCESS_BUDGET = "You have selected: %s, this costs: $%.2f\n"
            + "Your remaining budget is $%.2f\nYou still have $%.2f to spend today:)";
    private static final Logger logger = LogsCenter.getLogger(SelectItemCommand.class);

    private final Optional<String> foodName;
    private final Optional<Index> index;

    /**
     * @param index of the food in the filtered food list to edit
     */
    public SelectItemCommand(Index index) {
        requireNonNull(index);
        this.index = Optional.of(index);
        this.foodName = Optional.empty();
    }

    /**
     * @param foodName from the given food name
     */
    public SelectItemCommand(String foodName) {
        requireNonNull(foodName);
        this.index = Optional.empty();
        this.foodName = Optional.of(foodName);
    }

    @Override
    public CommandResult execute(Model model) throws IOException {
        requireNonNull(model);
        String nameOfFood = "";
        float priceOfFood = 0;
        if (index.isPresent()) {
            List<Food> foodList = model.getFilteredFoodList();
            Food food = foodList.get(index.get().getZeroBased());
            nameOfFood = food.getName();
            priceOfFood = food.getPrice();
            logger.info("Enter " + food.getName());
        } else if (foodName.isPresent()) {
            List<Food> foodList = model.getFilteredFoodList();
            for (Food f : foodList) {
                if (f.getName().equalsIgnoreCase(foodName.get())) {
                    nameOfFood = foodName.get();
                    priceOfFood = f.getPrice();
                    break;
                }
            }
        }
        if (model.getBudget().isPresent()) {
            Budget savedBudget = model.getBudget().get();
            savedBudget.subtractFromRemainingBudget(priceOfFood);

            model.setBudget(savedBudget);
            ReadOnlyFoodieBot foodieBot = model.getFoodieBot();
            Path budgetFilePath = new UserPrefs().getBudgetFilePath();

            JsonUtil.saveJsonFile(new JsonAdaptedBudget(foodieBot), budgetFilePath);

            Budget newBudget = model.getBudget().get();

            return new CommandResult(COMMAND_WORD, String.format(
                    MESSAGE_SUCCESS_BUDGET, nameOfFood,
                    priceOfFood,
                    newBudget.getRemainingBudget(),
                    newBudget.getRemainingDailyBudget()));
        } else {
            return new CommandResult(COMMAND_WORD, String.format(
                    MESSAGE_SUCCESS, nameOfFood, priceOfFood));
        }
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
