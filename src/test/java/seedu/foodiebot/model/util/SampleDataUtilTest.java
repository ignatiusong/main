package seedu.foodiebot.model.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class SampleDataUtilTest {
    @Test
    public void sampleData_returnsFoodieBot() {
        assertNotNull(SampleDataUtil.getSampleFoodieBot());
    }
}
