package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.canteen.Canteen;

/** An UI component that displays information of a {@code Canteen}. */
public class DirectionsToCanteenPanel extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(DirectionsToCanteenPanel.class);
    private static final String FXML = "DirectionsToCanteenCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *     AddressBook level 4</a>
     */
    private Canteen canteen;

    @FXML private HBox id;
    @FXML private ImageView iv;
    @FXML private Label textArea;
    @FXML private FlowPane tags;

    public DirectionsToCanteenPanel() {
        super(FXML);
    }

    /** */
    public void fillView(Canteen canteen) {
        this.canteen = canteen;
        iv.setImage(canteen.getDirectionImage());
        textArea.setText(canteen.getDirectionsText());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DirectionsToCanteenPanel)) {
            return false;
        }

        // state check
        DirectionsToCanteenPanel card = (DirectionsToCanteenPanel) other;
        return canteen.equals(card.canteen);
    }
}
