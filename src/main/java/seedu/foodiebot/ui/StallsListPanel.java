package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.canteen.Stall;

/**
 * Panel containing the list of stalls.
 */
public class StallsListPanel extends UiPart<Region> {
    private static final String FXML = "SimpleListViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StallsListPanel.class);

    @FXML
    private ListView<Stall> simpleListView;

    public StallsListPanel(ObservableList<Stall> stallList) {
        super(FXML);
        simpleListView.setItems(stallList);
        simpleListView.setCellFactory(listView -> new StallListViewCell());
    }

    public ListView<Stall> getSimpleListView() {
        return simpleListView;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stall} using a {@code
     * CanteenCard}.
     */
    class StallListViewCell extends ListCell<Stall> {
        @Override
        protected void updateItem(Stall stall, boolean empty) {
            super.updateItem(stall, empty);

            if (empty || stall == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StallCard(stall, getIndex() + 1).getRoot());
            }
        }
    }
}
