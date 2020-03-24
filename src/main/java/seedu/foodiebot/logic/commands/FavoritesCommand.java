package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.model.Model.PREDICATE_SHOW_ALL;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;

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

    /**
     * @param index of the canteen in the filtered stall list to edit
     */
    public FavoritesCommand(Index index) {
        requireNonNull(index);
        this.index = Optional.of(index);
    }
    public FavoritesCommand() {
        this.index = Optional.empty();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (index.isPresent()) {
            List<Food> foodList = model.getFilteredFoodList();
            Food food = foodList.get(index.get().getZeroBased());
            model.setFavorite(food);
            return new CommandResult(COMMAND_WORD, String.format(MESSAGE_SET_SUCCESS, food.getName()));
        } else {
            model.updateFilteredFavoriteList(PREDICATE_SHOW_ALL);
            return new CommandResult(COMMAND_WORD, MESSAGE_VIEW_SUCCESS, false, false);
        }
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
