package seedu.foodiebot.model;

import javafx.collections.ObservableList;
import seedu.foodiebot.model.canteen.Canteen;

/** Unmodifiable view of an foodiebot */
public interface ReadOnlyFoodieBot {

    /**
     * Returns an unmodifiable view of the canteens list. This list will not contain any duplicate
     * canteens.
     */
    ObservableList<Canteen> getCanteenList();
}
