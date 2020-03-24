package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.food.Food;

/**
 * Panel containing the list of foods.
 */
public class FoodListPanel extends UiPart<Region> {
    private static final String FXML = "SimpleListViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FoodListPanel.class);

    @FXML
    private ListView<Food> simpleListView;

    public FoodListPanel(ObservableList<Food> foodList) {
        super(FXML);
        simpleListView.setItems(foodList);
        simpleListView.setCellFactory(listView -> new FoodListViewCell());
    }

    public ListView<Food> getSimpleListView() {
        return simpleListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Food} using a {@code
     * FoodCard}.
     */
    class FoodListViewCell extends ListCell<Food> {
        @Override
        protected void updateItem(Food food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FoodCard(food, getIndex() + 1).getRoot());
            }
        }
    }
}
