package seedu.foodiebot;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MainTest {
    /** No error is thrown on launch. */
    public static void main(String[] args) {
        Exception exception = null;
        try {
            Main.main(args);
        } catch (Exception e) {
            exception = e;
        }
        assertNotNull(Main.class);
        assertNull(exception);
    }
}
