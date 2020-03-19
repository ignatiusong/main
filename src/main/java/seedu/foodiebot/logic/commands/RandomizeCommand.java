package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;

import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.randomize.Randomize;

/** Randomize a food item for the user based on index. */
public class RandomizeCommand extends Command {
    public static final String COMMAND_WORD = "randomize";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generate a random food option.";

    public static final String MESSAGE_SUCCESS = "Here are the choices: \n%s";

    private final Randomize randomize;

    private String prefix;
    private String action;

    public RandomizeCommand(String prefix, String action) {
        this.prefix = prefix;
        this.action = action;
        this.randomize = new Randomize(prefix, action);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, IOException {

        requireNonNull(model);
        FileReader fileC = model.listOfCanteens();
        FileReader fileS = model.listOfStalls();
        if (prefix.contains("c")) {
            randomize.setCanteens(fileC);
            randomize.getOptionsByCanteen(fileS);
        } else {
            randomize.getOptions(fileS);
        }
        return new CommandResult(COMMAND_WORD, String.format(MESSAGE_SUCCESS, randomize.output()));
    }
    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
