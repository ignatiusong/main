package seedu.foodiebot.ui;

import javafx.stage.Stage;

import seedu.foodiebot.logic.Logic;
import seedu.foodiebot.model.report.Report;

/** ReportScene class for creating a javafx scene. */
public class ReportScene extends BaseScene {

    public static final String FXML_FILE_FOLDER = "/view/";
    private Logic logic;
    private Stage primaryStage;

    public ReportScene(Stage primaryStage, Logic logic) {
        super(primaryStage, logic);
        this.primaryStage = primaryStage;
        this.logic = logic;
        handleReport(logic.getReport());
    }

    /**
     * Fills the handleReport region.
     */
    @javafx.fxml.FXML
    public void handleReport(Report report) {
        fillInnerParts();
        ReportPanel reportPanel = new ReportPanel();
        addToListPanel(reportPanel);
        reportPanel.fillView(report);
    }

}
