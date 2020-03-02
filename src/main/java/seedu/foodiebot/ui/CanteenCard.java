package seedu.foodiebot.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.foodiebot.model.canteen.Canteen;

/** An UI component that displays information of a {@code Canteen}. */
public class CanteenCard extends UiPart<Region> {
    private static final String FXML = "CanteenListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *     AddressBook level 4</a>
     */
    public final Canteen canteen;

    @FXML private HBox cardPane;
    @FXML private Label name;
    @FXML private ImageView iv;
    @FXML private Label id;
    @FXML private Label numberOfStalls;
    @FXML private Label distance;
    @FXML private Label nearestBlockName;
    @FXML private FlowPane tags;

    public CanteenCard(Canteen canteen, int displayedIndex) {
        super(FXML);
        this.canteen = canteen;
        id.setText(displayedIndex + ". ");
        iv.setImage(canteen.getCanteenImage());
        name.setText(canteen.getName().fullName);
        nearestBlockName.setText(canteen.getBlockName());
        numberOfStalls.setText(String.valueOf(canteen.getNumberOfStalls()));
        distance.setText(String.valueOf(canteen.getDistance()));
        canteen.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CanteenCard)) {
            return false;
        }

        // state check
        CanteenCard card = (CanteenCard) other;
        return id.getText().equals(card.id.getText()) && canteen.equals(card.canteen);
    }
}
