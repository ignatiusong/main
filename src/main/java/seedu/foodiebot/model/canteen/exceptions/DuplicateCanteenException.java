package seedu.foodiebot.model.canteen.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if
 * they have the same identity).
 */
public class DuplicateCanteenException extends RuntimeException {
    public DuplicateCanteenException() {
        super("Operation would result in duplicate persons");
    }
}
