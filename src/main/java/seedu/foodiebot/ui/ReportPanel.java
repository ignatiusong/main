package seedu.foodiebot.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.model.report.Report;

/** A UI component that displays information of a {@code Report}. */
public class ReportPanel extends UiPart<Region> {
    private static final Logger logger = LogsCenter.getLogger(DirectionsToCanteenPanel.class);
    private static final String FXML = "ReportCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *     AddressBook level 4</a>
     */

    private Report report;

    @FXML private HBox id;
    @FXML private Label textArea;

    public ReportPanel() {
        super(FXML);
    }

    /** Fills the view with the report. */
    public void fillView(Report report) {
        this.report = report;
        textArea.setText(report.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReportPanel)) {
            return false;
        }

        // state check
        ReportPanel card = (ReportPanel) other;
        return report.equals(card.report);
    }
}
