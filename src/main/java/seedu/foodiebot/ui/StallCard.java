package seedu.foodiebot.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.foodiebot.model.canteen.Stall;

/**
 * An UI component that displays information of a {@code Stall}.
 */
public class StallCard extends UiPart<Region> {
    private static final String FXML = "StallListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     * AddressBook level 4</a>
     */
    public final Stall stall;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private ImageView iv;
    @FXML
    private Label id;
    @FXML
    private Label stallNumber;
    @FXML
    private Label cuisine;
    @FXML
    private Label overallPriceRating;
    @FXML private FlowPane tags;

    public StallCard(Stall stall, int displayedIndex) {
        super(FXML);
        this.stall = stall;
        id.setText(displayedIndex + ". ");
        iv.setImage(stall.getStallImage());
        name.setText(stall.getName().fullName);
        stallNumber.setText(String.valueOf(stall.getStallNumber()));
        cuisine.setText(stall.getCuisine());
        overallPriceRating.setText(stall.getOverallPriceRating());
        try {
            stall.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } catch (ClassCastException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StallCard)) {
            return false;
        }

        // state check
        StallCard card = (StallCard) other;
        return id.getText().equals(card.id.getText()) && stall.equals(card.stall);
    }
}
