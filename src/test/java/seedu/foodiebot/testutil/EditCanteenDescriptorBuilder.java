package seedu.foodiebot.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.foodiebot.logic.commands.EditCommand;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.tag.Tag;

/**
 * A utility class to help with building EditCanteenDescriptor objects.
 */
public class EditCanteenDescriptorBuilder {

    private EditCommand.EditCanteenDescriptor descriptor;

    public EditCanteenDescriptorBuilder() {
        descriptor = new EditCommand.EditCanteenDescriptor();
    }

    public EditCanteenDescriptorBuilder(EditCommand.EditCanteenDescriptor descriptor) {
        this.descriptor = new EditCommand.EditCanteenDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditCanteenDescriptorBuilder(Canteen canteen) {
        descriptor = new EditCommand.EditCanteenDescriptor();
        descriptor.setTags(canteen.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditCanteenDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code
     * EditPersonDescriptor} that we are building.
     */
    public EditCanteenDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditCanteenDescriptor build() {
        return descriptor;
    }
}
