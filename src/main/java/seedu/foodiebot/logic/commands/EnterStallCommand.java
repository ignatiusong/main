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
import seedu.foodiebot.model.randomize.Randomize;

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
    private Optional<Index> index;

    private Randomize randomize = Randomize.checkRandomize();


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
        String currContext = ParserContext.getCurrentContext();
        String currentCanteenName = "";
        if (!currContext.equals("RANDOMIZE")) {
            currentCanteenName = ParserContext.getCurrentCanteen().get().getName().toString();
        } else {
            try {
                int idx = Integer.parseInt(stallName.get());
                index = Optional.of(Index.fromOneBased(idx));
            } catch (NumberFormatException ne) {
                ne.printStackTrace();
            }
        }
        if (index.isPresent()) {
            Stall stall;
            if (currContext.equals("RANDOMIZE")) {
                stall = model.getFilteredRandomizeList().get(index.get().getZeroBased());
                currentCanteenName = stall.getCanteenName();
            } else {
                stall = model.getFilteredStallList().get(index.get().getZeroBased());
            }
            ParserContext.setStallContext(stall);
            logger.info("Enter " + stall.getName());
            String finalCurrentCanteenName = currentCanteenName;
            model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(stall.getName().toString())
                    && f.getCanteen().equals(finalCurrentCanteenName));
        } else if (stallName.isPresent()) {
            List<Stall> stalls;
            if (currContext.equals("RANDOMIZE")) {
                stalls = randomize.getOptionsList();
            } else {
                stalls = model.getFilteredStallList();
            }
            boolean found = false;
            for (Stall s : stalls) {
                if (s.getName().toString().equalsIgnoreCase(stallName.get())) {
                    currentCanteenName = s.getCanteenName();
                    ParserContext.setStallContext(s);
                    //Might have 2 stalls from 2 canteens with same name
                    String finalCurrentCanteenName1 = currentCanteenName;
                    model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(s.getName().toString())
                            && f.getCanteen().equals(finalCurrentCanteenName1));
                    found = true;
                    break;
                }
            }
            if (!found) {
                return new CommandResult(COMMAND_WORD, Stall.MESSAGE_CONSTRAINTS);
            }
        }
        return new CommandResult(COMMAND_WORD, "Stall menu of  "
            + ParserContext.getCurrentStall().get().getName());
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

