package seedu.foodiebot.logic.parser;

import java.util.Optional;

import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Stall;

/**
 * Shows the context that the Parser is currently parsing in
 */
public class ParserContext {
    public static final String MAIN_CONTEXT = "MAIN";
    public static final String CANTEEN_CONTEXT = "CANTEEN";
    public static final String STALL_CONTEXT = "STALL";
    public static final String DIRECTIONS_CONTEXT = "GOTO";
    public static final String TRANSACTIONS_CONTEXT = "TRANSACTIONS";
    public static final String INVALID_CONTEXT_MESSAGE = "Wrong Context to Call Message. Current Context: ";
    public static final String FAVORITE_CONTEXT = "FAVORITE";

    private static String currentContext = ParserContext.MAIN_CONTEXT;
    private static String previousContext = ParserContext.MAIN_CONTEXT;

    private static Optional<Canteen> currentCanteen = Optional.empty();
    private static Optional<Stall> currentStall = Optional.empty();

    public static String getCurrentContext() {
        return currentContext;
    }

    public static String getPreviousContext() {
        return previousContext;
    }

    public static void setCurrentContext(String context) {
        previousContext = currentContext;
        currentContext = context;
    }

    public static Optional<Canteen> getCurrentCanteen() {
        return currentCanteen;
    }

    public static void setCurrentCanteen(Optional<Canteen> canteen) {
        currentCanteen = canteen;
    }

    public static Optional<Stall> getCurrentStall() {
        return currentStall;
    }

    public static void setCurrentStall(Optional<Stall> stall) {
        currentStall = stall;
    }

    public static void setCanteenContext(Canteen canteen) {
        //ParserContext.setCurrentContext(ParserContext.CANTEEN_CONTEXT);
        /**I comment this out because in EnterCanteenCommand, if you
         * enter the canteen with text eg. enter The Deck, you change canteen context
         * inside the command. However, you cannot change the currentContext as
         * there is a check in MainWindow for EnterCanteenCommand, whether it is MAIN or
         * CANTEEN, so should only change currentContext after it passes through check
         */
        ParserContext.setCurrentCanteen(Optional.of(canteen));
    }

    public static void setStallContext(Stall stall) {
        //ParserContext.setCurrentContext(ParserContext.STALL_CONTEXT);
        ParserContext.setCurrentStall(Optional.of(stall));
    }

}
