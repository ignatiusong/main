package seedu.foodiebot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private static final String command = "somecommand";
    private CommandResult commandResult = new CommandResult(command, "feedback");
    @Test
    public void equals() {

        CommandResult commandResult = new CommandResult(command, "feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(command, "feedback")));
        assertTrue(commandResult.equals(new CommandResult(command, "feedback",
            false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult(command, "different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(command, "feedback",
            true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(command, "feedback",
            false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult(command, "feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult(command, "feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(command, "different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(
            commandResult.hashCode(), new CommandResult(command, "feedback", true, false)
                .hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(
            commandResult.hashCode(), new CommandResult(command, "feedback", false, true)
                .hashCode());
    }

    @Test
    void isShowHelp() {
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    void isExit() {
        assertFalse(commandResult.isExit());
    }

    @Test
    void isLocationSpecified() {
        assertFalse(commandResult.isLocationSpecified());
    }
}
