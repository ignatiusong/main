package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.CanteenStub;

/** Adds a canteen to FoodieBot. */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Adds a person to the address book. "
                    + "Parameters: "
                    + PREFIX_NAME
                    + "NAME "
                    + "["
                    + PREFIX_TAG
                    + "TAG]...\n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + PREFIX_NAME
                    + "John Doe "
                    + PREFIX_TAG
                    + "friends "
                    + PREFIX_TAG
                    + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the address book";

    private final CanteenStub toAdd;

    /** Creates an AddCommand to add the specified {@code Person} */
    public AddCommand(CanteenStub canteen) {
        requireNonNull(canteen);
        toAdd = canteen;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        //        if (model.hasCanteen(toAdd)) {
        //            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        //        }
        //
        //        model.addCanteen(toAdd);
        return new CommandResult(COMMAND_WORD, String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
