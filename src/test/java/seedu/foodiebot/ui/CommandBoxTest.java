package seedu.foodiebot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import seedu.foodiebot.logic.commands.CommandResult;

/** Adapted from https://github.com/se-edu/addressbook-level4/blob/master/
 * src/test/java/seedu/address/ui/CommandBoxTest.java.*/
@ExtendWith(ApplicationExtension.class)
class CommandBoxTest {
    private CommandBox commandBox;
    private ArrayList<String> defaultStyleOfCommandBox;
    private ArrayList<String> errorStyleOfCommandBox;

    /* Create the ui card. */
    @BeforeEach
    public void setupSpec() throws Exception {
        FxToolkit.registerPrimaryStage();
    }

    /** Create the ui card. */
    @Start
    private void start(Stage stage) {
        StackPane stackPane = new StackPane();
        commandBox = new CommandBox(commandText ->
                new CommandResult("", ""));
        stackPane.getChildren().add(commandBox.getRoot());
        stage.setScene(new Scene(stackPane));
        stage.show();

        defaultStyleOfCommandBox = new ArrayList<>(commandBox.getCommandTextField().getStyleClass());

        errorStyleOfCommandBox = new ArrayList<>(defaultStyleOfCommandBox);
        errorStyleOfCommandBox.add(CommandBox.ERROR_STYLE_CLASS);
    }

    @Test
    public void commandBox_startingWithSuccessfulCommand() {
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
    }

    /**
     * Runs a command that fails, then verifies that <br>
     *      - the text remains <br>
     *      - the command box's style is the same as {@code errorStyleOfCommandBox}.
     */
    private void assertBehaviorForFailedCommand() {
        assertEquals("", commandBox.getCommandTextField().getText().trim());
        assertNotEquals(errorStyleOfCommandBox, commandBox.getCommandTextField().getStyleClass());
    }

    /**
     * Runs a command that succeeds, then verifies that <br>
     *      - the text is cleared <br>
     *      - the command box's style is the same as {@code defaultStyleOfCommandBox}.
     */
    private void assertBehaviorForSuccessfulCommand() {
        assertEquals("", commandBox.getCommandTextField().getText().trim());
        assertEquals(defaultStyleOfCommandBox, commandBox.getCommandTextField().getStyleClass());
    }
}
