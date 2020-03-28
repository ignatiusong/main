package seedu.foodiebot.model.budget;

import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_DAY;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_MONTH;
import static seedu.foodiebot.logic.parser.CliSyntax.PREFIX_DATE_BY_WEEK;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import seedu.foodiebot.commons.core.date.DateRange;
import seedu.foodiebot.logic.parser.exceptions.ParseException;

/**
 * Represents a person's budget.
 */
public class Budget {
    private static final String DAILY = PREFIX_DATE_BY_DAY.toString();
    private static final String WEEKLY = PREFIX_DATE_BY_WEEK.toString();
    private static final String MONTHLY = PREFIX_DATE_BY_MONTH.toString();

    private final float totalBudget;
    private float remainingBudget;
    private final String duration;
    private final LocalDateTime dateTimeOfCreation;
    private DateRange cycleRange;


    /**
     * Constructs a Budget object.
     * @param totalBudget The value of the budget.
     * @param remainingBudget The value of the remaining budget.
     * @param duration The duration cycle of the budget.
     * @param dateTimeOfCreation The date and time this object was created.
     * @param cycleRange The date range of the budget cycle.
     */
    public Budget(float totalBudget, float remainingBudget, String duration,
                  LocalDateTime dateTimeOfCreation, DateRange cycleRange) {
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
        this.duration = duration;
        this.dateTimeOfCreation = dateTimeOfCreation;
        this.cycleRange = cycleRange;
    }


    /**
     * Constructs a Budget object.
     * @param totalBudget The value of the budget.
     * @param remainingBudget The value of the remaining budget.
     * @param duration The duration cycle of the budget.
     * @param dateTimeOfCreation The date and time this object was created.
     * @param cycleStartDate The start date of the budget cycle.
     * @param cycleEndDate The end date of the budget cycle.
     */
    public Budget(float totalBudget, float remainingBudget, String duration,
                  LocalDateTime dateTimeOfCreation,
                  LocalDate cycleStartDate, LocalDate cycleEndDate) {
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
        this.duration = duration;
        this.dateTimeOfCreation = dateTimeOfCreation;

        DateRange range;
        try {
            range = DateRange.of(cycleStartDate, cycleEndDate);
        } catch (ParseException pe) {
            range = null;
        }
        this.cycleRange = range;
    }

    public Budget(float totalBudget, float remainingBudget, String duration,
                  LocalDateTime dateTimeOfCreation) {
        this.totalBudget = totalBudget;
        this.remainingBudget = remainingBudget;
        this.duration = duration;
        this.dateTimeOfCreation = dateTimeOfCreation;
        this.cycleRange = setCycleRange(duration, dateTimeOfCreation.toLocalDate());
    }


    /**
     * Constructs a {@code Budget} object.
     * @param totalBudget The value of the budget.
     * @param duration The duration cycle of the budget.
     */
    public Budget(float totalBudget, String duration) {
        this.totalBudget = totalBudget;
        this.remainingBudget = totalBudget;
        this.duration = duration;
        this.dateTimeOfCreation = LocalDateTime.now();
        this.cycleRange = setCycleRange(duration);
    }

    /**
     * Constructs an empty default budget.
     */
    public Budget() {
        this(Float.MAX_VALUE, DAILY);
    }

    /** Sets a DateRange based on the duration of the budget cycle and the system date.
     * @param duration The duration of the budget cycle.
     * @param date the start date of the budget cycle.
     * @return a DateRange representing the range of dates in the budget cycle.
     */
    private DateRange setCycleRange(String duration, LocalDate date) {
        try {
            if (duration.equals(DAILY)) {
                return DateRange.ofSingle(date);

            } else if (duration.equals(WEEKLY)) {
                return DateRange.of(date, date.plusWeeks(1).minusDays(1));

            } else if (duration.equals(MONTHLY)) {
                return DateRange.of(date, date.plusMonths(1).minusDays(1));

            }

        } catch (ParseException pe) {
            return null;
        }
        return null;
    }

    /** Sets a DateRange based on the duration of the budget cycle and the system date. */
    private DateRange setCycleRange(String duration) {
        return setCycleRange(duration, LocalDate.now());
    }

    /** Resets the remaining budget to  */
    public void resetRemainingBudget() {
        this.remainingBudget = this.totalBudget;
        this.cycleRange = setCycleRange(this.duration);
    }

    /**
     * Divides the remaining budget by 5 if set to weekly or 20 if set to monthly,
     * to get an average daily budget for weekdays.
     * @return An average daily budget.
     */
    public float getRemainingDailyBudget() {
        if (duration.equals(WEEKLY)) {
            return this.remainingBudget / 5;
        } else if (duration.equals(MONTHLY)) {
            return this.remainingBudget / 20;
        } else {
            return this.remainingBudget;
        }
    }

    public float getTotalBudget() {
        return this.totalBudget;
    }

    public float getRemainingBudget() {
        return this.remainingBudget;
    }

    public String getDuration() {
        return this.duration;
    }

    public String getDurationAsString() {
        return this.duration.equals(DAILY)
                ? "daily"
                : this.duration.equals(WEEKLY)
                    ? "weekly"
                    : "monthly";
    }
    /** Return the date where the budget is created. */
    public LocalDate getDateOfCreation() {
        // return this.dateOfCreation;
        return this.dateTimeOfCreation.toLocalDate();
    }

    /** Return the time when the budget is created. */
    public LocalTime getTimeOfCreation() {
        // return this.timeOfCreation;
        return this.dateTimeOfCreation.toLocalTime();
    }

    public LocalDateTime getDateTimeOfCreation() {
        return this.dateTimeOfCreation;
    }

    /** Return the cycle for which this budget is for. */
    public DateRange getCycleRange() {
        return this.cycleRange;
    }

    /** . */
    public void subtractFromRemainingBudget(float expenses) {
        this.remainingBudget -= expenses;
        if (this.remainingBudget < 0) {
            this.remainingBudget = 0;
        }
    }

    /** . */
    public void addToRemainingBudget(float expenses) {
        this.remainingBudget += expenses;
        if (this.remainingBudget > this.totalBudget) {
            this.remainingBudget = this.totalBudget;
        }
    }
    /** Check if this is a default value */
    public boolean isDefaultBudget() {
        return this.totalBudget == Float.MAX_VALUE;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return otherBudget.getTotalBudget() == totalBudget
                && otherBudget.getRemainingBudget() == remainingBudget
                && otherBudget.getDuration().equals(duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalBudget, remainingBudget, duration, dateTimeOfCreation, cycleRange);
    }




}
