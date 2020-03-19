package seedu.foodiebot.commons.core.date;

import java.time.LocalDate;

/** Contains static dates with a definite value. */
public class DefiniteDate {
    public static final LocalDate MIN_DATE = LocalDate.of(1970, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(2199, 12, 31);
}
