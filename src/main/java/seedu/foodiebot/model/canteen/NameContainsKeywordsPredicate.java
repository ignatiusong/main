package seedu.foodiebot.model.canteen;

import java.util.List;
import java.util.function.Predicate;

import seedu.foodiebot.commons.util.StringUtil;

/** Tests that a {@code Block}'s {@code Name} matches any of the keywords given. */
public class NameContainsKeywordsPredicate implements Predicate<Canteen> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Canteen canteen) {
        return keywords.stream()
            .anyMatch(
                keyword ->
                    StringUtil.containsWordIgnoreCase(
                        canteen.getBlockName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals((
                                (NameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
