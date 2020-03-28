package seedu.foodiebot.logic.commands;

/** This class specifies the action name specific to the command result. */
public class ActionCommandResult extends CommandResult {

    public final String action;
    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, and other
     * fields set to their default value.
     * @param commandName
     * @param action
     * @param feedbackToUser
     */
    public ActionCommandResult(String commandName, String action, String feedbackToUser) {
        super(commandName , feedbackToUser);
        this.action = action;
    }
}
