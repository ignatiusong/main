package seedu.foodiebot.commons.core.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ConceptualDateTest {
    @Test
    public void enums_validNames() {
        assertEquals("START_DATE", ConceptualDate.START_DATE.name());
        assertEquals("END_DATE", ConceptualDate.END_DATE.name());
    }
}
