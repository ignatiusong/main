package seedu.foodiebot.logic.commands;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.tag.Tag;

/**
 * Filters a list of canteens or stalls to only display things which have a particular keyword
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + "Parameters: "
                    + "TAG \n"
                    + "Example: "
                    + COMMAND_WORD
                    + " "
                    + "Western";

    public static final String MESSAGE_SUCCESS = "";

    public static final String MESSAGE_CONSTRAINTS = "Filter argument cannot be empty";

    private Tag tag;

    public FilterCommand(String tag) {
        this.tag = new Tag(tag);
    }

    @Override
    public CommandResult execute(Model model) {
        String context = ParserContext.getCurrentContext();
        if (context.equals(ParserContext.MAIN_CONTEXT)) {
            model.updateFilteredCanteenList(s -> s.getTags().contains(this.tag));
        } else if (context.equals(ParserContext.CANTEEN_CONTEXT)) {
            Canteen canteen = ParserContext.getCurrentCanteen().get();
            model.updateFilteredStallList(s -> s.getCanteenName().equalsIgnoreCase(
                    canteen.getName().toString()) && s.getTags().contains(this.tag));
        }
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
    }

    @Override
    public boolean needToSaveCommand() {
        return false;
    }
}
