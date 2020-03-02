package seedu.foodiebot.logic.commands;

import seedu.foodiebot.model.canteen.Canteen;

/** */
public class DirectionsCommandResult extends CommandResult {

    public final Canteen canteen;
    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}, and other
     * fields set to their default value.
     *
     * @param feedbackToUser
     */
    public DirectionsCommandResult(Canteen canteen, String feedbackToUser) {
        super("goto" , feedbackToUser);
        this.canteen = canteen;
    }
}
