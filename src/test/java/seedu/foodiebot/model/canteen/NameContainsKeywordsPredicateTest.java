package seedu.foodiebot.model.canteen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.testutil.CanteenBuilder;

public class NameContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate =
            new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate =
            new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
            new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("Deck"));
        Canteen canteen = new CanteenBuilder().withNearestBlock("Deck NusFlavors").build();
        assertTrue(predicate.test(canteen));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Frontier", "CentralSquare"));
        assertTrue(predicate.test(new CanteenBuilder().withNearestBlock("Frontier CentralSquare").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("PGPRaircon", "Platypus"));
        assertTrue(predicate.test(new CanteenBuilder().withNearestBlock("PGPRaircon TheDeck").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("fInEFood", "FlAvors"));
        assertTrue(predicate.test(new CanteenBuilder().withNearestBlock("FineFood Flavors").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate =
            new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CanteenBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CanteenBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate =
            new NameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(
            predicate.test(
                new CanteenBuilder()
                    .withName("Alice")
                    .build()));
    }
}
