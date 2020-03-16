package seedu.foodiebot.testutil;

import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.foodiebot.logic.commands.EditCommand.EditCanteenDescriptor;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.tag.Tag;

/**
 * A utility class for Canteen.
 */
public class StallUtil {
    /**
     * Returns the part of command string for the given {@code Canteen}'s details.
     */
    public static String getCanteenDetails(Canteen canteen) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + canteen.getName().fullName + " ");
        canteen.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCanteenDescriptor}'s details.
     */
    public static String getEditCanteenDescriptorDetails(EditCanteenDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor
            .getName()
            .ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
