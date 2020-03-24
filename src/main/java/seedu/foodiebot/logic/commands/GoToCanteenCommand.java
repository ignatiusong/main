package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_FROM_DATE;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;

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
            + PREFIX_FROM_DATE
            + "NEAREST_BLOCK_NAME]...\n"
            + "Example: "
            + COMMAND_WORD
            + " "
            + "deck "
            + PREFIX_FROM_DATE
            + "com1 ";

    public static final String MESSAGE_SUCCESS = "";
    private static final Logger logger = LogsCenter.getLogger(GoToCanteenCommand.class);
    private final Optional<Index> index;
    private final Optional<String> canteenName;
    private final String nearestBlockName;

    /**
     * @param index of the canteen in the filtered canteen list to edit
     */
    public GoToCanteenCommand(Index index, String nearestBlockName) {
        requireNonNull(index);
        this.index = Optional.of(index);
        this.canteenName = Optional.empty();
        this.nearestBlockName = nearestBlockName;
    }

    public GoToCanteenCommand(String canteenName, String nearestBlockName) {
        requireNonNull(canteenName);
        this.index = Optional.empty();
        this.canteenName = Optional.of(canteenName);
        this.nearestBlockName = nearestBlockName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Canteen> lastShownList = model.getFilteredCanteenList();
        Optional<Canteen> result = Optional.empty();

        if (index.isPresent()) {
            Canteen canteen = lastShownList.get(index.get().getZeroBased());
            result = lastShownList.stream()
                .filter(c -> c.getBlockName().equalsIgnoreCase(nearestBlockName)
                    && c.getName().equals(canteen.getName()))
                .findFirst();

        } else if (canteenName.filter(Predicate.not(String::isEmpty)).isPresent()) {
            result = lastShownList.stream()
                .filter(c -> c.getBlockName().equalsIgnoreCase(nearestBlockName)
                    && c.getName().equals(new Name(canteenName.get())))
                .findFirst();
        }
        if (result.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NOTAVAILABLE);
        }
        return new DirectionsCommandResult(result.get(), MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof GoToCanteenCommand)) {
            return false;
        }

        GoToCanteenCommand otherCanteen = (GoToCanteenCommand) other;
        return otherCanteen.nearestBlockName.equalsIgnoreCase(nearestBlockName);
    }
}
