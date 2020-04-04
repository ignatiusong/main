package seedu.foodiebot.logic.parser;

/** Contains Command Line Interface (CLI) syntax definitions common to multiple commands */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DATE_BY_DAY = new Prefix("d/");
    public static final Prefix PREFIX_DATE_BY_MONTH = new Prefix("m/");
    public static final Prefix PREFIX_DATE_BY_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_DATE_BY_WEEK = new Prefix("w/");
    public static final Prefix PREFIX_FROM = new Prefix("f/");
    public static final Prefix PREFIX_TO = new Prefix("t/");
    public static final Prefix PREFIX_NOOFSTALLS = new Prefix("st/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_CANTEEN = new Prefix("c/");
}
