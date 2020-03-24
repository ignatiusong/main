package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.transaction.PurchasedFood;

/**
 * Panel containing the list of purchased foods.
 */
public class TransactionsPanel extends UiPart<Region> {
    private static final String FXML = "SimpleListViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TransactionsPanel.class);

    @FXML
    private ListView<PurchasedFood> simpleListView;

    public TransactionsPanel(ObservableList<PurchasedFood> foodList) {
        super(FXML);
        simpleListView.setItems(foodList);
        simpleListView.setCellFactory(listView -> new TransactionsPanel.TransactionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PurchasedFood} using a {@code
     * TransactionsCard}.
     */
    class TransactionListViewCell extends ListCell<PurchasedFood> {
        @Override
        protected void updateItem(PurchasedFood food, boolean empty) {
            super.updateItem(food, empty);

            if (empty || food == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TransactionsCard(food, getIndex() + 1).getRoot());
            }
        }
    }
}
