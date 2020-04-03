package seedu.foodiebot.model.report;

import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.foodiebot.model.transaction.PurchasedFood;

/** Generates a report of transactions */
public class Report {
    private static final String TOTAL_SPENT = "Total amount spent: $%.2f";

    private float totalSpent;
    private final TreeMap<PurchasedFood, Integer> report;

    /** Constructs a new Report object. */
    public Report() {
        this.totalSpent = 0;
        this.report = new TreeMap<PurchasedFood, Integer>();
    }

    /**
     * Creates a new report filtered by the given predicate.
     * @param transactions The full list of transactions.
     * @param predicate The predicate to filter the date range of the report.
     */
    public void create(ObservableList<PurchasedFood> transactions, Predicate<PurchasedFood> predicate) {
        reset();
        for (PurchasedFood food : transactions) {
            if (predicate.test(food)) {
                addFood(food);
            }
        }
    }

    /**
     * Adds a purchased food into the report map.
     * @param food The purchased food.
     */
    public void addFood(PurchasedFood food) {
        if (report.containsKey(food)) {
            int counter = report.get(food);
            counter++;
            report.replace(food, counter);
        } else {
            report.put(food, 1);
        }
        totalSpent = totalSpent + food.getPrice();
    }

    /**
     * Resets the report to an empty report.
     */
    public void reset() {
        totalSpent = 0;
        report.clear();
    }

    private float getTotalSpent() {
        return totalSpent;
    }

    /**
     * Returns true if both canteens have the same identity and data fields. This defines a stronger
     * notion of equality between two canteens.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Report)) {
            return false;
        }

        Report otherReport = (Report) other;
        return otherReport.getTotalSpent() == getTotalSpent();
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(totalSpent, report);
    }
    @Override
    public String toString() {
        String header = String.format(TOTAL_SPENT, totalSpent) + "\n"
                + "-----------------------------" + "\n";

        String currentCanteen = "";
        String currentStall = "";

        String output = "";

        for (PurchasedFood food : report.keySet()) {
            if (!currentCanteen.equals(food.getCanteen())) {
                currentCanteen = food.getCanteen();
                output = output + "\nCanteen: " + currentCanteen + "\n";
            }

            if (!currentStall.equals(food.getStallName())) {
                currentStall = food.getStallName();
                output = output + "\n    Stall: " + currentStall + "\n";
            }

            // print food
            output = output + "\n        Food: " + food.getName() + "\n"
                    + "            - Price: " + String.format("$%.2f", (float) food.getPrice()) + "\n"
                    + "            - No. of times you've been here: " + report.get(food) + " time(s)" + "\n"
                    + "            - Total spent here: " + String.format("$%.2f", (
                            (float) food.getPrice() * report.get(food))) + "\n";

        }

        return header + output;
    }

}
