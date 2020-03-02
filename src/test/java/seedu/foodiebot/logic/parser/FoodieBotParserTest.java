package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.foodiebot.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.ClearCommand;
import seedu.foodiebot.logic.commands.ExitCommand;
import seedu.foodiebot.logic.commands.FindCommand;
import seedu.foodiebot.logic.commands.HelpCommand;
import seedu.foodiebot.logic.commands.ListCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.canteen.NameContainsKeywordsPredicate;

public class FoodieBotParserTest {

    private final FoodieBotParser parser = new FoodieBotParser();

    /*
    @Test
    public void parseCommand_add() throws Exception {
        Canteen canteen = new CanteenBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CanteenUtil.getAddCommand(canteen));
        //assertEquals(new AddCommand(canteen), command);
    }

     */

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    /*@Test
    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command =
            (DeleteCommand)
                parser.parseCommand(
                    DeleteCommand.COMMAND_WORD
                        + " "
                        + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    /* @Test
    public void parseCommand_edit() throws Exception {
        Canteen canteen = new CanteenBuilder().build();
        EditCanteenDescriptor descriptor = new EditCanteenDescriptorBuilder(canteen).build();
        EditCommand command =
                (EditCommand)
                        parser.parseCommand(
                                EditCommand.COMMAND_WORD
                                        + " "
                                        + INDEX_FIRST_PERSON.getOneBased()
                                        + " "
                                        + CanteenUtil.getEditCanteenDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    */

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command =
            (FindCommand)
                parser.parseCommand(
                    FindCommand.COMMAND_WORD
                        + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
            ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(
            ParseException.class,
            MESSAGE_UNKNOWN_COMMAND, (
            ) -> parser.parseCommand("unknownCommand"));
    }
}
