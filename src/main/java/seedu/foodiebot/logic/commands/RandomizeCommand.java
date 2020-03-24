package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.randomize.Randomize;

/**
 * Randomize a food item for the user based on index.
 */
public class RandomizeCommand extends Command {
    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generate a random food option.";

    public static final String MESSAGE_SUCCESS = "";

    private final Randomize randomize;

    private String prefix;
    private String action = "";
    private Optional<Index> index;

    public RandomizeCommand(String prefix, String action) {
        this.prefix = prefix;
        this.action = action;
        this.randomize = new Randomize(prefix, action);
    }

    public RandomizeCommand(String prefix, Index index) {
        this.prefix = prefix;
        this.index = Optional.of(index);
        this.randomize = new Randomize(prefix, index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, IOException {

        requireNonNull(model);
        FileReader fileC = model.listOfCanteens();
        FileReader fileS = model.listOfStalls();
        if (prefix.contains("c")) {
            if (index.isPresent()) {
                List<Canteen> canteenList = model.getFilteredCanteenList();
                Canteen canteen = canteenList.get(index.get().getZeroBased());
                randomize.setCanteenIndex(canteen);
            } else {
                randomize.setCanteens(fileC);
            }
            randomize.getOptionsByCanteen(fileS);
        } else if (prefix.contains("t")) {
            randomize.setCanteens(fileC);
            randomize.getOptionsByTags(fileS);
        } else {
            randomize.getOptions(fileS);
        }
        return new CommandResult(COMMAND_WORD, randomize.output());
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
