package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.foodiebot.logic.commands.BudgetCommand;
import seedu.foodiebot.logic.commands.ClearCommand;
import seedu.foodiebot.logic.commands.Command;
import seedu.foodiebot.logic.commands.EnterCanteenCommand;
import seedu.foodiebot.logic.commands.ExitCommand;
import seedu.foodiebot.logic.commands.FavoritesCommand;
import seedu.foodiebot.logic.commands.FilterCommand;
import seedu.foodiebot.logic.commands.FindCommand;
import seedu.foodiebot.logic.commands.FoodMenuCommand;
import seedu.foodiebot.logic.commands.GoToCanteenCommand;
import seedu.foodiebot.logic.commands.HelpCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.commands.RandomizeCommand;
import seedu.foodiebot.logic.commands.ReportCommand;
import seedu.foodiebot.logic.commands.SelectItemCommand;
import seedu.foodiebot.logic.commands.TransactionsCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/** Parses user input. */
public class FoodieBotParser {

    /** Used for initial separation of command word and args. */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        /*case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);*/

        case GoToCanteenCommand.COMMAND_WORD:
            return new GoToCanteenCommandParser().parse(arguments);

        case EnterCanteenCommand.COMMAND_WORD:
            if (ParserContext.getCurrentContext().equals(ParserContext.MAIN_CONTEXT)) {
                return new EnterCanteenCommandParser().parse(arguments);
            } else {
                return new EnterStallCommandParser().parse(arguments);
            }

        case FoodMenuCommand.COMMAND_WORD:
            return new FoodMenuCommandParser().parse(arguments);

        case SelectItemCommand.COMMAND_WORD:
            return new SelectItemCommandParser().parse(arguments);

        case BudgetCommand.COMMAND_WORD:
            return new BudgetCommandParser().parse(arguments);

        case ReportCommand.COMMAND_WORD:
            return new ReportCommandParser().parse(arguments);

        case RandomizeCommand.COMMAND_WORD:
            return new RandomizeCommandParser().parse(arguments);

        case FavoritesCommand.COMMAND_WORD:
            return new FavoritesCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommand("Hello");

        case TransactionsCommand.COMMAND_WORD:
            return new TransactionsCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
