package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Stall;

/**
 * Represents a command telling the user to enter a particular stall
 */
public class EnterStallCommand extends Command {
    public static final String COMMAND_WORD = "enter";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "STALL_NAME \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "Taiwanese ";

    public static final String MESSAGE_SUCCESS = "";
    private static final Logger logger = LogsCenter.getLogger(EnterStallCommand.class);

    private final Optional<String> stallName;
    private final Optional<Index> index;

    /**
     * @param index of the canteen in the filtered stall list to edit
     */
    public EnterStallCommand(Index index) {
        requireNonNull(index);
        this.index = Optional.of(index);
        this.stallName = Optional.empty();
    }

    /**
     * @param stallName from the given canteen name
     */
    public EnterStallCommand(String stallName) {
        requireNonNull(stallName);
        this.index = Optional.empty();
        this.stallName = Optional.of(stallName);
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String currentCanteenName = ParserContext.getCurrentCanteen().get().getName().toString();
        if (index.isPresent()) {
            Stall stall = model.getFilteredStallList().get(index.get().getZeroBased());
            ParserContext.setStallContext(stall);
            logger.info("Enter " + stall.getName());
            model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(stall.getName().toString())
                    && f.getCanteen().equals(currentCanteenName));

        } else if (stallName.isPresent()) {
            List<Stall> stalls = model.getFilteredStallList();
            for (Stall s : stalls) {
                if (s.getName().toString().equalsIgnoreCase(stallName.get())) {
                    ParserContext.setStallContext(s);
                    //Might have 2 stalls from 2 canteens with same name
                    model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(s.getName().toString())
                            && f.getCanteen().equals(currentCanteenName));
                    break;
                }
            }
        }
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EnterStallCommand)) {
            return false;
        }

        EnterStallCommand otherCanteen = (EnterStallCommand) other;
        return otherCanteen.index.orElseGet(() -> Index.fromZeroBased(0))
            .equals(index.orElseGet(() -> Index.fromZeroBased(0)))
            && otherCanteen.stallName.orElseGet(() -> "").equals(stallName.orElseGet(() -> ""));
    }
}

