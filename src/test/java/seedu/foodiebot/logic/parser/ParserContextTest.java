package seedu.foodiebot.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.model.util.SampleDataUtil;

class ParserContextTest {
    @Test
    public void setStall_to_context() {
        ParserContext.setStallContext(SampleDataUtil.getSampleStalls()[0]);
        assertEquals(ParserContext.getCurrentStall().get(), SampleDataUtil.getSampleStalls()[0]);

        ParserContext.setCanteenContext(SampleDataUtil.getSampleCanteens()[0]);
        assertEquals(ParserContext.getCurrentCanteen().get(), SampleDataUtil.getSampleCanteens()[0]);

        ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        assertEquals(ParserContext.getCurrentContext(), ParserContext.STALL_CONTEXT);
        ParserContext.setCurrentContext(ParserContext.MAIN_CONTEXT);
        assertEquals(ParserContext.getPreviousContext(), ParserContext.STALL_CONTEXT);
    }
}
