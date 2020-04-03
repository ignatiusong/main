package seedu.foodiebot.logic.commands;

import static seedu.foodiebot.logic.parser.ParserContext.INVALID_CONTEXT_MESSAGE;

import seedu.foodiebot.logic.parser.ParserContext;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;
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

    public static final String MESSAGE_SUCCESS = "Successfully filtered canteens";

    public static final String MESSAGE_CONSTRAINTS = "Filter argument cannot be empty";

    private Tag tag;
    private Integer price;
    private boolean hasTagError;

    public FilterCommand(String tag) {
        try {
            this.tag = new Tag(tag);
        } catch (IllegalArgumentException ex) {
            hasTagError = true;
        }
    }

    public FilterCommand(int price) {
        this.price = price;
    }

    @Override
    public CommandResult execute(Model model) {
        String context = ParserContext.getCurrentContext();
        if (hasTagError) {
            return new CommandResult(COMMAND_WORD, Tag.MESSAGE_CONSTRAINTS);
        }
        if (price != null) {
            if (ParserContext.getCurrentContext().equals(ParserContext.STALL_CONTEXT)) {
                Stall stall = ParserContext.getCurrentStall().get();

                model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(
                        stall.getName().toString()) && f.getPrice() <= price);

                return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);

            } else {
                return new CommandResult(COMMAND_WORD, INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
            }
        }

        if (context.equals(ParserContext.MAIN_CONTEXT)) {
            model.updateFilteredCanteenList(s -> s.getTags().contains(this.tag));
        } else if (context.equals(ParserContext.CANTEEN_CONTEXT)) {
            Canteen canteen = ParserContext.getCurrentCanteen().get();
            model.updateFilteredStallList(s -> s.getCanteenName().equalsIgnoreCase(
                    canteen.getName().toString()) && s.getTags().contains(this.tag));
        } else if (context.equals(ParserContext.STALL_CONTEXT)) {
            Stall stall = ParserContext.getCurrentStall().get();
            model.updateFilteredFoodList(f -> f.getStallName().equalsIgnoreCase(
                    stall.getName().toString()) && f.getTags().contains(this.tag));
        } else {
            return new CommandResult(COMMAND_WORD, INVALID_CONTEXT_MESSAGE + ParserContext.getCurrentContext());
        }
        return new CommandResult(COMMAND_WORD, MESSAGE_SUCCESS);
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

        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilter = (FilterCommand) other;
        //TODO replace with strong equality
        return true;
    }
}
