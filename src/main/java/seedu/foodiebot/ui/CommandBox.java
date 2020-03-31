package seedu.foodiebot.ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fxmisc.richtext.InlineCssTextField;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.logic.commands.CommandResult;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.canteen.Block;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {
    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    private static final Logger logger = LogsCenter.getLogger(CommandBox.class);
    protected boolean match = false;
    private final CommandExecutor commandExecutor;

    @FXML
    private InlineCssTextField commandTextField;

    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                String pattern = "goto [a-zA-Z0-9]+ f\\/[^.]";
                if (this.match && commandTextField.getText().matches(pattern)) {
                    String text = commandTextField.getText().substring(commandTextField.getText().indexOf('/') + 1);
                    String userEntered = commandTextField.getText().substring(0,
                        commandTextField.getText().indexOf('/') + 1);

                    for (String s : Block.BLOCKS) {
                        if (s.toLowerCase().startsWith(text.stripLeading())) {
                            commandTextField.setText(userEntered.concat(s));
                            commandTextField.requestFocus();
                            commandTextField.setOnKeyPressed(event -> {
                                if (event.getCode() == KeyCode.BACK_SPACE) {
                                    commandTextField.clear();
                                    commandTextField.setText(userEntered);
                                }
                            });
                            commandTextField.setStyle(0, commandTextField.getText().indexOf('/') + 1,
                                "-fx-fill: #fff;");
                            commandTextField.setStyle(commandTextField.getText().indexOf('/') + 1,
                                commandTextField.getText().length() , "-fx-fill: #696969;");
                            logger.log(Level.INFO, text.stripLeading());
                            this.match = false;
                            break;
                        }
                    }


                } else {
                    pattern = "goto [a-zA-Z0-9]+ f[(\\/]$";
                    if (commandTextField.getText().matches(pattern)) {
                        this.match = true;
                        commandTextField.setOnKeyPressed(event -> {
                            if (event.getCode() == KeyCode.BACK_SPACE) {

                            }

                            if (event.getCode() == KeyCode.DELETE) {
                                commandTextField.clear();
                            }
                        });
                    }
                }
            }
        );
    }

    public InlineCssTextField getCommandTextField() {
        return commandTextField;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            commandExecutor.execute(commandTextField.getText());
            commandTextField.setText("");
        } catch (CommandException | ParseException | IOException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
        commandTextField.setStyle(0, commandTextField.getText().length(), "-fx-fill: #d06651");
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.foodiebot.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException, IOException;
    }
}
