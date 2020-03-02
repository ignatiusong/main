package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;

/**
 * Get the directions to the canteen through a given location specified with prefix: /f.
 */
public class GoToCanteenCommand extends Command {
    public static final String COMMAND_WORD = "goto";
    public static final String MESSAGE_USAGE =
        COMMAND_WORD
            + ": Get the directions to the canteen. "
            + "Parameters: "
            + "CANTEEN_NAME "
            + "["
            + PREFIX_FROM
            + "NEAREST_BLOCK_NAME]...\n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + "deck "
            + PREFIX_FROM
            + "com1 ";

    public static final String MESSAGE_SUCCESS = "";
    private static final Logger logger = LogsCenter.getLogger(GoToCanteenCommand.class);
    private final Index index;
    private final String nearestBlockName;

    /**
     * @param index of the canteen in the filtered person list to edit
     */
    public GoToCanteenCommand(Index index, String nearestBlockName) {
        requireNonNull(index);
        this.index = index;
        this.nearestBlockName = nearestBlockName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Canteen> lastShownList = model.getFilteredCanteenList();
        Canteen canteen = lastShownList.get(index.getZeroBased());


        Optional<Canteen> result = lastShownList.stream()
            .filter(c -> c.getBlockName().equalsIgnoreCase(nearestBlockName)
                && c.getName().equals(canteen.getName()))
            .findFirst();

        if (result.isEmpty()) {
            //model.updateFilteredCanteenList(Model.PREDICATE_SHOW_ALL_CANTEENS);
            throw new CommandException(Messages.MESSAGE_NOTAVAILABLE);
        } else {
            return new DirectionsCommandResult(canteen, MESSAGE_SUCCESS);
        }
    }
}
