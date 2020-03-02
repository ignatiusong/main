package seedu.foodiebot.model.canteen;

import java.util.Arrays;

/**
 * Represents the nearest block to the canteen in FoodieBot. Guarantees: details are present and not null
 */
public class Block {
    public static final String[] BLOCKS = new String[]{"PGPR", "UHC", "S17", "COM1", "UTown"};
    public static final String MESSAGE_CONSTRAINTS =
        "Block name should be from " + Arrays.toString(BLOCKS);

    // Identity fields
    private final Name name;

    public Block(Name name) {
        this.name = name;
    }

    /** checks if the block matches one of the listed names */
    public static boolean isValidBlock(String trimmedBlockName) {
        return Arrays.stream(BLOCKS)
            .anyMatch(trimmedBlockName::equalsIgnoreCase);
    }

    public Name getName() {
        return name;
    }
}
