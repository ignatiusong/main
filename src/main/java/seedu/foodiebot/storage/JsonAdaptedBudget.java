package seedu.foodiebot.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.foodiebot.model.FoodieBot;
import seedu.foodiebot.model.ReadOnlyFoodieBot;
import seedu.foodiebot.model.budget.Budget;

/** Jackson-friendly version of {@link Budget}. */
public class JsonAdaptedBudget {

    private final float totalBudget;
    private final float remainingBudget;
    private final String duration;
    private final LocalDateTime dateTimeOfCreation;
    private final LocalDate cycleRangeStart;
    private final LocalDate cycleRangeEnd;

    /** Constructs a {@code JsonAdaptedBudget} with the given person details. */
    @JsonCreator
    public JsonAdaptedBudget(
            @JsonProperty("totalBudget") String totalBudget,
            @JsonProperty("remainingBudget") String remainingBudget,
            @JsonProperty("budgetDuration") String duration,
            @JsonProperty("dateTimeOfCreation") String dateTimeOfCreation,
            @JsonProperty("cycleRangeStart") String cycleRangeStart,
            @JsonProperty("cycleRangeEnd") String cycleRangeEnd) {
        this.totalBudget = Float.parseFloat(totalBudget);
        this.remainingBudget = Float.parseFloat(remainingBudget);
        this.duration = duration;
        this.dateTimeOfCreation = LocalDateTime.parse(dateTimeOfCreation);
        this.cycleRangeStart = LocalDate.parse(cycleRangeStart);
        this.cycleRangeEnd = LocalDate.parse(cycleRangeEnd);
    }

    /** Converts a given {@code Budget} into this class for Jackson use. */
    public JsonAdaptedBudget(Budget source) {
        totalBudget = source.getTotalBudget();
        remainingBudget = source.getRemainingBudget();
        duration = source.getDuration();
        dateTimeOfCreation = source.getDateTimeOfCreation();
        cycleRangeStart = source.getCycleRange().getStartDate();
        cycleRangeEnd = source.getCycleRange().getEndDate();
    }

    /** Converts a given {@code Budget} into this class for Jackson use. */
    public JsonAdaptedBudget(ReadOnlyFoodieBot foodieBot) {
        this(foodieBot.getBudget());
    }


    /** Converts this Jackson-friendly adapted person object into the model's {@code Budget} object. */
    public FoodieBot toModelType() {
        FoodieBot foodieBot = new FoodieBot();

        Budget budget = new Budget(totalBudget, remainingBudget, duration, dateTimeOfCreation,
                cycleRangeStart, cycleRangeEnd);

        foodieBot.setBudget(budget);
        return foodieBot;
    }

}
