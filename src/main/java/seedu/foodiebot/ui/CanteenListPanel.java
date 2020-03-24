package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.canteen.Canteen;

/** Panel containing the list of canteens. */
public class CanteenListPanel extends UiPart<Region> {
    private static final String FXML = "SimpleListViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CanteenListPanel.class);

    @FXML private ListView<Canteen> simpleListView;

    public CanteenListPanel(ObservableList<Canteen> canteenList, boolean showDistanceField) {
        super(FXML);
        simpleListView.setItems(canteenList);
        simpleListView.setCellFactory(listView -> new CanteenListViewCell(showDistanceField));
    }

    public ListView<Canteen> getSimpleListView() {
        return simpleListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Canteen} using a {@code
     * CanteenCard}.
     */
    class CanteenListViewCell extends ListCell<Canteen> {
        private final boolean showDistanceField;
        protected CanteenListViewCell(boolean showDistanceField) {
            this.showDistanceField = showDistanceField;
        }
        @Override
        protected void updateItem(Canteen canteen, boolean empty) {
            super.updateItem(canteen, empty);

            if (empty || canteen == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CanteenCard(canteen, getIndex() + 1, showDistanceField).getRoot());
            }
        }
    }
}
