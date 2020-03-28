package seedu.foodiebot.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.foodiebot.commons.core.index.Index;
import seedu.foodiebot.commons.util.StringUtil;
import seedu.foodiebot.logic.commands.FilterCommand;
import seedu.foodiebot.logic.parser.exceptions.ParseException;
import seedu.foodiebot.model.canteen.Address;
import seedu.foodiebot.model.canteen.Block;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.tag.Tag;

/** Contains utility methods used for parsing strings in the various *Parser classes. */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed. If the index is more than the number of canteens throws
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer or
     * if the index is more than number of canteens.
     */
    public static Index parseCanteenIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        int index = Integer.parseInt(trimmedIndex);
        if (index <= 0 || index > Canteen.getNumCanteens()) {
            throw new IndexOutOfBoundsException(Canteen.INVALID_CANTEEN_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed. If the index is more than the number of canteens throws
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer or
     * if the index is more than number of canteens.
     */
    public static Index parseStallIndex(String oneBasedIndex, Canteen canteen)
            throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        int index = Integer.parseInt(trimmedIndex);
        if (index <= 0 || index > canteen.getNumberOfStalls()) {
            throw new IndexOutOfBoundsException(Canteen.INVALID_STALL_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name} into a numberOfStalls. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static int parseNoOfStalls(String numberOfStalls) throws ParseException {
        requireNonNull(numberOfStalls);

        return Integer.parseInt(numberOfStalls);
    }
    /**
     * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String blockName} into an {@code blockName}. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @throws ParseException if the given {@code blockName} is invalid.
     */
    public static String parseBlockName(String blockName) throws ParseException {
        requireNonNull(blockName);
        String trimmedBlockName = blockName.trim();
        if (!Block.isValidBlock(trimmedBlockName)) {
            throw new ParseException(Block.MESSAGE_CONSTRAINTS);
        }
        return trimmedBlockName;
    }

    /**
     * Parses a {@code String canteenName} into an {@code canteenName}. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @throws ParseException if the given {@code canteenName} is invalid.
     */
    public static String parseCanteenName(String canteenName) throws ParseException {
        requireNonNull(canteenName);
        String trimmedCanteenName = canteenName.trim();
        if (!Canteen.isValidCanteen(trimmedCanteenName)) {
            throw new ParseException(Canteen.MESSAGE_CONSTRAINTS);
        }
        return trimmedCanteenName;
    }
    /**
     * Parses a {@code String foodName} into a {@code foodName}. Leading and trailing whitespaces will
     * be trimmed
     *
     * @throws ParseException if the given {@code foodName} is invalid
     */
    public static String parseFoodName(String foodName) throws ParseException {
        requireNonNull(foodName);
        String trimmedFoodName = foodName.trim();
        if (!Food.isValidFood(trimmedFoodName)) {
            throw new ParseException(Food.MESSAGE_CONSTRAINTS);
        }
        return trimmedFoodName;

    }

    /**
     * Parses a {@code String foodName} into a {@code foodName}. Leading and trailing whitespaces will
     * be trimmed
     *
     * @throws ParseException if the given {@code foodName} is invalid
     */
    public static String parseFilterTag(String filterTag) throws ParseException {
        requireNonNull(filterTag);
        String trimmedFilterTag = filterTag.trim();
        if (trimmedFilterTag.isBlank()) {
            throw new ParseException(FilterCommand.MESSAGE_CONSTRAINTS);
        }
        return trimmedFilterTag;

    }


    /**
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /** Parses {@code Collection<String> tags} into a {@code Set<Tag>}. */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
