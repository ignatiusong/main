package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodiebot.commons.core.LogsCenter;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;

/**
 * Selects a canteen to view the food stalls.
 */
public class EnterCanteenCommand extends Command {
    public static final String COMMAND_WORD = "enter";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "CANTEEN_NAME \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "deck ";

    public static final String MESSAGE_SUCCESS = "";
    public static final String INVALID_INDEX_MESSAGE = "Please provide a valid index";
    private static final Logger logger = LogsCenter.getLogger(EnterCanteenCommand.class);

    private final Optional<String> canteenName;
    private final Optional<Index> index;

    /**
     * @param index of the canteen in the filtered stall list to edit
     */
    public EnterCanteenCommand(Index index) {
        requireNonNull(index);
        this.index = Optional.of(index);
        this.canteenName = Optional.empty();
    }

    /**
     * @param canteenName from the given canteen name
     */
    public EnterCanteenCommand(String canteenName) {
        requireNonNull(canteenName);
        this.index = Optional.empty();
        this.canteenName = Optional.of(canteenName);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        /* All the stalls are retrieved as they contain the canteen name field
           which we filter the canteen name that specified in enter {canteenName}
        */
        boolean found = false;
        if (index.isPresent()) {
            List<Canteen> canteenList;
            if (model.isLocationSpecified()) {
                canteenList = model.getFilteredCanteenListSortedByDistance();
            } else {
                canteenList = model.getFilteredCanteenList();
            }
            if (index.get().getZeroBased() <= canteenList.size()) {
                model.updateFilteredStallList(Model.PREDICATE_SHOW_ALL);
                Canteen canteen = canteenList.get(index.get().getZeroBased());
                ParserContext.setCanteenContext(canteen);
                ParserContext.setCurrentCanteen(Optional.of(canteen));
                logger.info("Enter " + canteen.getName());
                model.updateFilteredStallList(s -> s.getCanteenName().equalsIgnoreCase(
                        canteen.getName().toString()));
            } else {
                return new CommandResult(COMMAND_WORD, INVALID_INDEX_MESSAGE);
            }

        } else if (canteenName.isPresent()) {
            List<Canteen> canteens = model.getFilteredCanteenList();
            for (Canteen c : canteens) {
                if (c.getName().toString().equalsIgnoreCase(canteenName.get())) {
                    ParserContext.setCanteenContext(c);
                    model.updateFilteredStallList(s -> s.getCanteenName().equalsIgnoreCase(c.getName().toString()));
                    found = true;
                    break;
                }
            }
            if (!found) {
                return new CommandResult(COMMAND_WORD, Canteen.MESSAGE_CONSTRAINTS);
            }
        }
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EnterCanteenCommand)) {
            return false;
        }

        EnterCanteenCommand otherCanteen = (EnterCanteenCommand) other;
        return otherCanteen.index.orElseGet(() -> Index.fromZeroBased(0))
            .equals(index.orElseGet(() -> Index.fromZeroBased(0)))
            && otherCanteen.canteenName.orElseGet(() -> "").equals(canteenName.orElseGet(() -> ""));
    }
}
