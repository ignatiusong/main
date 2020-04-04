package seedu.foodiebot.ui;

import static java.util.logging.Level.INFO;

import java.io.IOException;
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
    protected boolean suggested = true;
    protected String lastMatch = "";

    private final CommandExecutor commandExecutor;

    @FXML
    private InlineCssTextField commandTextField;

    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        commandTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                String pattern = "goto [a-zA-Z0-9\\s}]+ f\\/[a-zA-Z0-9]+";
                if (suggested & commandTextField.getText().matches(pattern)) {
                    String text = "";
                    try {
                        text = commandTextField.getText().substring(commandTextField.getText().indexOf('/') + 1,
                            commandTextField.getText().indexOf('/') + 2 + lastMatch.length());
                    } catch (IndexOutOfBoundsException ex) {
                        logger.fine(ex.getLocalizedMessage());
                    }
                    String userEntered = commandTextField.getText().substring(0,
                        commandTextField.getText().indexOf('/') + 1);
                    logger.log(INFO, "text " + text);
                    this.match = false;
                    suggested = false;
                    for (String s : Block.BLOCKS) {
                        if (s.toLowerCase().startsWith(text.stripLeading().toLowerCase())) {
                            logger.log(INFO, "new " + newValue);
                            lastMatch = text;
                            logger.log(INFO, "lastMatch " + lastMatch);
                            if (lastMatch.length() > s.length()) {
                                continue;
                            }

                            String temp = userEntered.concat(lastMatch.toLowerCase())
                                .concat(s.substring(lastMatch.length()).toLowerCase());
                            commandTextField.setText(temp);
                            commandTextField.setStyle(0, commandTextField.getText()
                                    .indexOf('/') + 1 + lastMatch.length(),
                                "-fx-fill: #fff;");
                            commandTextField.setStyle(commandTextField.getText().indexOf('/') + 1 + lastMatch.length(),
                                commandTextField.getText().length(), "-fx-fill: #696969;");
                            commandTextField.setOnKeyPressed(event -> {
                                if (event.getCode() == KeyCode.BACK_SPACE) {
                                    String current = commandTextField.getText();
                                    if (commandTextField.getText().indexOf("/") > 1) {
                                        suggested = true;
                                        lastMatch = "";
                                        commandTextField.setText(current.substring(0, current.indexOf("/") + 1));
                                    } else {
                                        this.match = false;
                                        if (!lastMatch.isEmpty()) {
                                            lastMatch = lastMatch.substring(0, lastMatch.length() - 1);
                                        }
                                    }
                                }

                            });
                            this.match = true;
                            break;
                        }
                    }

                    if (!suggested) {
                        suggested = true;
                    }

                    if (!match) {
                        text = commandTextField.getText().substring(commandTextField.getText().indexOf('/') + 1,
                            commandTextField.getText().indexOf('/') + 2 + lastMatch.length());
                        String temp = userEntered.concat(text);
                        commandTextField.setText(temp);
                        suggested = false;

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
        commandTextField.setStyle(0, commandTextField.getText().length(), "-fx-fill: #fff");
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
        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                setStyleToDefault();
            }
        });
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
