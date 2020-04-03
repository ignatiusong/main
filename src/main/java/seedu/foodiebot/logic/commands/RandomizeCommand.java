package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.randomize.Randomize;

/**
 * Randomize a food item for the user based on index.
 */
public class RandomizeCommand extends Command {
    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generate a random food option.";

    public static final String MESSAGE_SUCCESS = "Here are the choices:\n";

    private final Randomize randomize;

    private Messages messages;

    private String prefix;
    private String action = "";
    private Optional<Index> index;

    public RandomizeCommand(String prefix, String action, Randomize randomize) {
        this.prefix = prefix;
        this.action = action;
        this.randomize = Randomize.checkRandomize();
    }

    public RandomizeCommand(String prefix, Index index, Randomize randomize) {
        this.prefix = prefix;
        this.index = Optional.of(index);
        this.randomize = Randomize.checkRandomize();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, IOException {
        requireNonNull(model);
        ParserContext.setCurrentContext(ParserContext.RANDOMIZE_CONTEXT);

        randomize.setPrefix(prefix);
        randomize.setAction(action);

        FileReader fileC = model.listOfCanteens();
        FileReader fileS = model.listOfStalls();
        randomize.resetInternalList();
        if (prefix.contains("c")) {
            if (index != null) { //null and isPresent are different, DO NOT change
                List<Canteen> canteenList = model.getFilteredCanteenList();
                Canteen canteen = canteenList.get(index.get().getZeroBased());
                randomize.setCanteenIndex(canteen);
            } else {
                randomize.setCanteens(fileC);
                if (!randomize.ifCanteenNamePresent()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_CANTEEN_NAME);
                }
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RandomizeCommand)) {
            return false;
        }

        RandomizeCommand otherRandomize = (RandomizeCommand) other;
        return action.equals(otherRandomize.action) & randomize == otherRandomize.randomize;
    }
}
