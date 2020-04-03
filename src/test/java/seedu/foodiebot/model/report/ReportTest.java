package seedu.foodiebot.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.foodiebot.model.util.SampleDataUtil.getTagSet;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.transaction.PurchasedFood;
import seedu.foodiebot.model.util.SampleDataUtil;

class ReportTest {

    @Test
    public void are_reports_equal() {
        Report report = new Report();
        Report copy = new Report();

        Canteen canteen = SampleDataUtil.getSampleCanteens()[0];
        assertEquals(report.hashCode(), copy.hashCode());

        assertFalse(report.equals(canteen));

        assertTrue(report.equals(report));

        report.addFood(new PurchasedFood("ComboSet", 5, "1 Meat, 1 Pasta, 2 Sides", "Combo Set.png",
            1, "Nus Flavors", "Western", getTagSet("asian"), LocalDate.of(2020, 03, 12),
            LocalTime.of(20, 20), new Rating(5), new Review("review")));
        assertNotEquals(report.toString(), copy.toString());
    }
}
