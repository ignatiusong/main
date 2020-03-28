package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_FAVORITE_NAME_SPECIFIED;
import static seedu.foodiebot.logic.parser.ParserContext.FAVORITE_CONTEXT;
import static seedu.foodiebot.model.Model.PREDICATE_SHOW_ALL;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.food.Food;

/**
 * Displays the food items favorited by the user.
 */
public class FavoritesCommand extends Command {
    public static final String COMMAND_WORD = "favorites";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD;

    public static final String MESSAGE_VIEW_SUCCESS = "Listed all favorites";
    public static final String MESSAGE_SET_SUCCESS = "Favorited %s";


    private static final Logger logger = LogsCenter.getLogger(FavoritesCommand.class);

    private final Optional<Index> index;
    private final String action;

    /**
     * @param index of the canteen in the filtered stall list to edit
     */
    public FavoritesCommand(Index index, String action) {
        requireNonNull(index);
        this.index = Optional.of(index);
        this.action = action;
    }
    public FavoritesCommand(String action) {
        this.index = Optional.empty();
        this.action = action;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        switch (action) {
        case "set":
            if (index.isPresent()) {
                List<Food> foodList = model.getFilteredFoodList();
                if (!foodList.isEmpty()) {
                    int indexInteger = index.get().getZeroBased();
                    if (indexInteger + 1 > foodList.size()) {
                        return new ActionCommandResult(COMMAND_WORD, action, Food.INVALID_FOOD_INDEX);
                    }
                    Food food = foodList.get(indexInteger);
                    model.setFavorite(food);
                    return new ActionCommandResult(COMMAND_WORD, action,
                        String.format(MESSAGE_SET_SUCCESS, food.getName()));
                }
            }
            return new ActionCommandResult(COMMAND_WORD, action,
                MESSAGE_INVALID_FAVORITE_NAME_SPECIFIED);
        case "view":
            ParserContext.setCurrentContext(FAVORITE_CONTEXT);
            ObservableList<Food> list = model.getFilteredFavoriteFoodList(false);
            model.updateFilteredFavoriteList(PREDICATE_SHOW_ALL);
            return new ActionCommandResult(COMMAND_WORD, action, MESSAGE_VIEW_SUCCESS);
        case "remove":
            if (index.isPresent()) {
                List<Food> foodList = model.getFilteredFoodList();
                if (!foodList.isEmpty()) {
                    int indexInteger = index.get().getZeroBased();
                    if (indexInteger + 1 > foodList.size()) {
                        return new ActionCommandResult(COMMAND_WORD, action, Food.INVALID_FOOD_INDEX);
                    }
                    Food food = foodList.get(indexInteger);
                    model.removeFavorite(food);
                    return new ActionCommandResult(COMMAND_WORD, action,
                        String.format(MESSAGE_SET_SUCCESS, food.getName()));
                }
            }
            return new ActionCommandResult(COMMAND_WORD, action,
                MESSAGE_INVALID_FAVORITE_NAME_SPECIFIED);
        default:
            break;
        }
        return new ActionCommandResult(COMMAND_WORD, action,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoritesCommand.MESSAGE_USAGE));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavoritesCommand)) {
            return false;
        }

        FavoritesCommand otherFavorite = (FavoritesCommand) other;
        return otherFavorite.index.orElseGet(() -> Index.fromZeroBased(0))
            .equals(index.orElseGet(() -> Index.fromZeroBased(0)));
    }
}
