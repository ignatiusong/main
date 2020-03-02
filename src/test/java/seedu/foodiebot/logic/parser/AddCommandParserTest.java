package seedu.foodiebot.logic.parser;

import static seedu.foodiebot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodiebot.logic.commands.CommandTestUtil.VALID_NAME_DECK;
import static seedu.foodiebot.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.foodiebot.logic.commands.AddCommand;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
    //    CanteenStub expectedCanteen = new CanteenStub(new Name(VALID_NAME_DECK), 0, null);
    //        //new CanteenBuilder(DECK).withTags(VALID_TAG_ASIAN).build();
    //
    //
    //    assertParseSuccess(
    //        parser,
    //        PREAMBLE_NON_EMPTY
    //            + NAME_DESC_DECK
    //            + TAG_DESC_INDIAN,
    //        new AddCommand(expectedCanteen));

            // multiple tags - all accepted
    //    CanteenStub expectedPersonMultipleTags =
    //        new CanteenBuilder(DECK).withTags(VALID_TAG_ASIAN, VALID_TAG_INDIAN).build();
    //    assertParseSuccess(
    //        parser,
    //        PREAMBLE_NON_EMPTY
    //            + NAME_DESC_DECK
    //            + TAG_DESC_ASIAN
    //            + TAG_DESC_INDIAN,
    //  new AddCommand(expectedPersonMultipleTags));
    }

    //  @Test
    //  public void parse_optionalFieldsMissing_success() {
    //    // zero tags
    //      CanteenStub expectedCanteen = new CanteenStub(new Name(VALID_NAME_DECK), 0,null);
    //    assertParseSuccess(
    //        parser,
    //        NAME_DESC_DECK,
    //        new AddCommand(expectedCanteen));
    //  }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(
            parser,
            VALID_NAME_DECK,
            expectedMessage);


    }

    /*
    @Test
    public void parse_invalidValue_failure() {
    // invalid name
    assertParseFailure(
        parser,
        INVALID_NAME_DESC +
        PREAMBLE_NON_EMPTY
            + NAME_DESC_DECK
            + TAG_DESC_ASIAN
            + TAG_DESC_INDIAN,
        Name.MESSAGE_CONSTRAINTS);


    // invalid tag
    assertParseFailure(
        parser,
        PREAMBLE_NON_EMPTY
            + NAME_DESC_DECK
            + TAG_DESC_ASIAN
            + TAG_DESC_INDIAN,
        Tag.MESSAGE_CONSTRAINTS);

    // two invalid values, only first invalid value reported
    assertParseFailure(
        parser,
        INVALID_NAME_DESC ,
        Name.MESSAGE_CONSTRAINTS);

    // non-empty preamble
    assertParseFailure(
        parser,
        PREAMBLE_NON_EMPTY
            + NAME_DESC_DECK
            + TAG_DESC_ASIAN
            + TAG_DESC_INDIAN,
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
  }

   */
}
