package seedu.foodiebot.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.commons.util.CollectionUtil;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.Model;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.tag.Tag;
import seedu.foodiebot.model.util.SampleDataUtil;

/** Edits the details of an existing person in the address book. */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD
                    + ": Edits the details of the person identified "
                    + "by the index number used in the displayed person list. "
                    + "Existing values will be overwritten by the input values.\n"
                    + "Parameters: INDEX (must be a positive integer) "
                    + "["
                    + PREFIX_NAME
                    + "NAME] "
                    + "["
                    + PREFIX_TAG
                    + "TAG]...\n"
                    + "Example: "
                    + COMMAND_WORD
                    + " 1 ";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "This person already exists in the address book.";

    private final Index index;
    private final EditCanteenDescriptor editCanteenDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editCanteenDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditCanteenDescriptor editCanteenDescriptor) {
        requireNonNull(index);
        requireNonNull(editCanteenDescriptor);

        this.index = index;
        this.editCanteenDescriptor = new EditCanteenDescriptor(editCanteenDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Canteen> lastShownList = model.getFilteredCanteenList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        Canteen canteenToEdit = lastShownList.get(index.getZeroBased());
        Canteen editedCanteen = createEditedPerson(canteenToEdit, editCanteenDescriptor);

        /*if (!canteenToEdit.isSameCanteen(editedCanteen) && model.hasCanteen(editedCanteen)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setCanteen(canteenToEdit, editedCanteen);
        model.updateFilteredCanteenList(PREDICATE_SHOW_ALL_CANTEENS);
         */
        return new CommandResult(COMMAND_WORD, String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedCanteen));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit} edited with
     * {@code editPersonDescriptor}.
     */
    private static Canteen createEditedPerson(
            Canteen canteenToEdit, EditCanteenDescriptor editCanteenDescriptor) {
        assert canteenToEdit != null;

        Name updatedName = editCanteenDescriptor.getName().orElse(canteenToEdit.getName());
        int updatedNumberOfStalls = editCanteenDescriptor.getNumberOfStalls();
        int updatedDistance = editCanteenDescriptor.getDistance();
        String updatedBlockName = editCanteenDescriptor.getNearestBlockName();
        Set<Tag> updatedTags = editCanteenDescriptor.getTags().orElse(canteenToEdit.getTags());

        return SampleDataUtil.getSampleCanteens()[0];
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index) && editCanteenDescriptor.equals(e.editCanteenDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCanteenDescriptor {
        private Name name;
        private int numberOfStalls;
        private int distance;
        private String nearestblockName;
        private Set<Tag> tags;

        public EditCanteenDescriptor() {}

        /** Copy constructor. A defensive copy of {@code tags} is used internally. */
        public EditCanteenDescriptor(EditCanteenDescriptor toCopy) {
            setName(toCopy.name);
            setNumberOfStalls(toCopy.numberOfStalls);
            setDistance(toCopy.distance);
            setNearestBlockName(toCopy.nearestblockName);
            setTags(toCopy.tags);
        }

        /** Returns true if at least one field is edited. */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setNumberOfStalls(int numberOfStalls) {
            this.numberOfStalls = numberOfStalls;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void setNearestBlockName(String blockName) {
            this.nearestblockName = blockName;
        }

        public int getNumberOfStalls() {
            return numberOfStalls;
        }

        public int getDistance() {
            return distance;
        }

        public String getNearestBlockName() {
            return nearestblockName;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used
         * internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if
         * modification is attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null)
                    ? Optional.of(Collections.unmodifiableSet(tags))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCanteenDescriptor)) {
                return false;
            }

            // state check
            EditCanteenDescriptor e = (EditCanteenDescriptor) other;

            return getName().equals(e.getName())
                    && getNumberOfStalls() == (e.getNumberOfStalls())
                    && getDistance() == (e.getDistance())
                    && getNearestBlockName().equals(e.getNearestBlockName())
                    && getTags().equals(e.getTags());
        }
    }
}
