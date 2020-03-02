package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.canteen.Canteen;

/** Panel containing the list of persons. */
public class CanteenListPanel extends UiPart<Region> {
    private static final String FXML = "CanteenListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CanteenListPanel.class);

    @FXML private ListView<Canteen> canteenListView;

    public CanteenListPanel(ObservableList<Canteen> canteenList) {
        super(FXML);
        canteenListView.setItems(canteenList);
        canteenListView.setCellFactory(listView -> new CanteenListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Canteen} using a {@code
     * CanteenCard}.
     */
    class CanteenListViewCell extends ListCell<Canteen> {
        @Override
        protected void updateItem(Canteen canteen, boolean empty) {
            super.updateItem(canteen, empty);

            if (empty || canteen == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CanteenCard(canteen, getIndex() + 1).getRoot());
            }
        }
    }
}
