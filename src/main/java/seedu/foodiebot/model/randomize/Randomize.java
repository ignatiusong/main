package seedu.foodiebot.model.randomize;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.logic.commands.exceptions.CommandException;
import seedu.foodiebot.model.canteen.Canteen;
import seedu.foodiebot.model.canteen.Name;
import seedu.foodiebot.model.canteen.Stall;
import seedu.foodiebot.model.tag.Tag;

/**
 * Randomize a canteen, stall and food choice for the user
 */
public class Randomize {

    private static Randomize singleRandomize = null;

    private String selectedCanteen = "";
    private String selectedStall = "";
    private List<String[]> listOutput = new ArrayList<>();
    private List<Stall> optionsList = new ArrayList<>();
    private StringBuilder output = new StringBuilder("Here are the choices:\n");
    private JSONArray jsonArrayStall;
    private Map<String, Integer> mapStalls = new HashMap<>();
    private Boolean isLessThanFiveOption = false;
    private Random rand = new Random();
    private String prefix;
    private String action;

    private Randomize() {}

    /**
     * This method ensure that there is only 1 randomize object.
     * @return Randomize
     */
    public static Randomize checkRandomize() {
        if (singleRandomize == null) {
            singleRandomize = new Randomize();
        }
        return singleRandomize;
    }

    /**
     * This method clears all the list items.
     */
    public void resetInternalList() {
        this.optionsList.clear();
        this.listOutput.clear();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * This method creates an UnmodifiableObservableList for the BaseScene.
     * @return ObservableList with stall object.
     */
    public ObservableList<Stall> asUnmodifiableObservableList() {
        ObservableList<Stall> internalList = FXCollections.observableArrayList(optionsList);
        ObservableList<Stall> internalUnmodifiableList =
                FXCollections.unmodifiableObservableList(internalList);
        System.out.println(internalList);
        resetInternalList();
        return internalUnmodifiableList;
    }

    /**
     * This method return the output list option.
     *
     * @return String of options.
     */
    public String output() {
        if (listOutput.isEmpty() && prefix.contains("t")) {
            output.append("There are no stall with this tag: ")
                    .append(action);
        } else if (isLessThanFiveOption) {
            output.append("There are less than 5 stores in this canteen. This is all the result.");
        }
        return output.toString();
    }

    /**
     * This function save canteen name and number of stalls from the jsonfile (foodiebot.json).
     *
     * @param file which is provided from the RandomizeCommand
     */
    public void setCanteens(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            JSONArray canteenList = (JSONArray) obj.get("canteens");
            String canteenName;
            int numOfStalls;
            for (Object o : canteenList) {
                JSONObject canteen = (JSONObject) o;
                canteenName = (String) canteen.get("name");
                numOfStalls = (int) (long) canteen.get("numberOfStalls");
                mapStalls.put(canteenName, numOfStalls);
            }
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This function save canteen name and number of stalls from the canteen selected by index.
     *
     * @param canteen which is provided from the RandomizeCommand
     */
    public void setCanteenIndex(Canteen canteen) {
        selectedCanteen = canteen.getName().toString();
        action = selectedCanteen;
        int numOfStalls = canteen.getNumberOfStalls();
        mapStalls.put(selectedCanteen, numOfStalls);
    }

    /**
     * This method produce a list of options by Canteen from jsonfile (foodiebot-stalls.json).
     *
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptions(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            jsonArrayStall = (JSONArray) obj.get("stalls");
            List<Stall> listStall = generateListByCriteria("", "");
            generateFiveOptions(listStall);
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This method produce a list of options from the jsonfile (foodiebot-stalls.json).
     *
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptionsByCanteen(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            jsonArrayStall = (JSONArray) obj.get("stalls");
            generateOptionsByCanteen(action);
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This method generate the list od options by the selected canteen.
     *
     * @param canteenName The user input on the selected canteen.
     */
    private void generateOptionsByCanteen(String canteenName) throws CommandException {
        selectedCanteen = canteenName;
        List<Stall> matchingCanteen = generateListByCriteria("canteen", canteenName);
        try {
            int canteenSize = mapStalls.get(canteenName);
            if (canteenSize >= 5) {
                isLessThanFiveOption = false;
                generateFiveOptions(matchingCanteen);
            } else {
                isLessThanFiveOption = true;
                for (int i = 0; i < canteenSize; i++) {
                    Stall stall = matchingCanteen.get(i);
                    selectedStall = stall.getName().toString();
                    listOutput.add(new String[]{selectedCanteen, selectedStall});
                    optionsList.add(stall);
                }
            }
        } catch (NullPointerException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CANTEEN_NAME);
        }
    }

    /**
     * This method add the option to the list
     *
     * @param listOfStalls List of Stall object with the matching canteen.
     */
    private void generateFiveOptions(List<Stall> listOfStalls) {
        while (listOutput.size() < 5) {
            int index = rand.nextInt(listOfStalls.size());
            Stall stall = listOfStalls.get(index);
            selectedCanteen = stall.getCanteenName();
            selectedStall = stall.getName().toString();
            listOutput.add(new String[]{selectedCanteen, selectedStall});
            listOfStalls.remove(index);
            optionsList.add(stall);
        }
    }

    /**
     * This method check if the current option already exist in the list.
     *
     * @param canteen This is the selected canteen
     * @param stall   This is the selected stall
     * @return Boolean true if option is not on the list, false otherwise
     */
    private Boolean isNotInList(String canteen, String stall) {
        boolean state = true;
        for (String[] text : listOutput) {
            if (canteen.equals(text[0]) && stall.equals(text[1])) {
                state = false;
                break;
            }
        }
        return state;
    }

    /**
     * This method produce a list of options from the jsonfile (foodiebot-stalls.json).
     *
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptionsByTags(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            jsonArrayStall = (JSONArray) obj.get("stalls");
            generateListByTag(action);
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This method generate the list od options by the selected canteen.
     *
     * @param tag The user input on the selected tag.
     */
    private void generateListByTag(String tag) {
        List<Stall> matchingTag = generateListByCriteria("tags", tag);
        if (matchingTag.size() >= 5) {
            isLessThanFiveOption = false;
            generateFiveOptions(matchingTag);
        } else {
            isLessThanFiveOption = true;
            for (int i = 0; i < matchingTag.size(); i++) {
                Stall stall = matchingTag.get(i);
                selectedStall = stall.getName().toString();
                selectedCanteen = stall.getCanteenName();
                listOutput.add(new String[]{selectedCanteen, selectedStall});
                optionsList.add(stall);
            }
        }
    }

    /**
     * This method return a list of stalls object that satisfy the criteria.
     *
     * @param type     define by which to filter by canteen or tags.
     * @param criteria more information on on type in details.
     * @return List of stalls that satisfy the type and criteria.
     */
    private List<Stall> generateListByCriteria(String type, String criteria) {
        List<Stall> listOfStallMatchingCriteria = new ArrayList<>();
        for (Object o : jsonArrayStall) {
            JSONObject jsonStall = (JSONObject) o;
            if (type.equals("tags")) {
                JSONArray tagList = (JSONArray) jsonStall.get("tags");
                if (tagList.contains(criteria)) {
                    listOfStallMatchingCriteria.add(createNewStall(jsonStall));
                }
            } else if (type.equals("canteen")) {
                String canteenName = (String) jsonStall.get("canteenName");
                if (canteenName.equals(criteria)) {
                    listOfStallMatchingCriteria.add(createNewStall(jsonStall));
                }
            } else {
                listOfStallMatchingCriteria.add(createNewStall(jsonStall));
            }
        }
        return listOfStallMatchingCriteria;
    }

    /**
     * This method create a new Stall Object.
     *
     * @param jsonStall JSONObject with the detail for a stall.
     * @return Stall object.
     */
    private Stall createNewStall(JSONObject jsonStall) {
        String name = (String) jsonStall.get("name");
        String canteenName = (String) jsonStall.get("canteenName");
        int stallNumber = ((Number) jsonStall.get("stallNumber")).intValue();
        String stallImageName = (String) jsonStall.get("stallImageName");
        String cuisine = (String) jsonStall.get("cuisine");
        String overallPriceRating = (String) jsonStall.get("overallPriceRating");
        int favorite = ((Number) jsonStall.get("favorite")).intValue();
        JSONArray tagsArr = (JSONArray) jsonStall.get("tags");
        Set<Tag> tags = new HashSet<>(tagsArr);

        return new Stall(new Name(name), canteenName, stallNumber, stallImageName,
                cuisine, overallPriceRating, favorite,
                tags, new ArrayList<>());
    }

}
