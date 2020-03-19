package seedu.foodiebot.model.randomize;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import seedu.foodiebot.commons.core.Messages;
import seedu.foodiebot.logic.commands.exceptions.CommandException;

/**
 * Randomize a canteen, stall and food choice for the user
 */
public class Randomize {

    private String selectedCanteen = "";
    private String selectedStall = "";
    private List<String[]> listOutput;
    private String output = "";
    private JSONArray stallList;
    private Map<String, Object> mapStalls = new HashMap<>();
    private Boolean isLessThanFiveOption = false;

    private Random rand = new Random();

    private String prefix;
    private String action;


    public Randomize(String prefix, String action) {
        this.prefix = prefix;
        this.action = action;
        this.listOutput = new ArrayList<>();
    }

    /**
     * This method return the output list option.
     * @return String of options.
     */
    public String output() {
        for (int i = 0; i < listOutput.size(); i++) {
            String[] text = listOutput.get(i);
            output = output + String.format("%d. %s: %s\n", i + 1, text[0], text[1]);
        }
        if (isLessThanFiveOption) {
            output += "There are less than 5 stores in this canteen. This is all the result.";
        }
        return output;
    }
    /**
     * This function select a canteen from the jsonfile (foodiebot.json).
     * @param file which is provided from the RandomizeCommand
     */
    public void setCanteens(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            JSONArray canteenList = (JSONArray) obj.get("canteens");
            int listSize = canteenList.size();
            String canteenName;
            Object numOfStalls;
            for (int i = 0; i < listSize; i++) {
                JSONObject canteen = (JSONObject) canteenList.get(i);
                canteenName = (String) canteen.get("name");
                numOfStalls = canteen.get("numberOfStalls");
                mapStalls.put(canteenName, numOfStalls);
            }
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This method produce a list of options by Canteen from jsonfile (foodiebot-stalls.json).
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptions(FileReader file) throws CommandException {
        int listSize;
        int index;
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            stallList = (JSONArray) obj.get("stalls");

            listSize = stallList.size();
            while (listOutput.size() < 5) {
                index = rand.nextInt(listSize);
                JSONObject stall = (JSONObject) stallList.get(index);
                selectedStall = (String) stall.get("name");
                selectedCanteen = (String) stall.get("canteenName");
                if (isNotInList(selectedCanteen, selectedStall)) {
                    listOutput.add(new String[]{selectedCanteen, selectedStall});
                }
            }
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }

    /**
     * This method produce a list of options from the jsonfile (foodiebot-stalls.json).
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptionsByCanteen(FileReader file) throws CommandException {
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            stallList = (JSONArray) obj.get("stalls");

            if (prefix.contains("c")) {
                generateListByCanteen(action);
            }
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }


    /**
     * This method generate the list od options by the selected canteen.
     * @param canteenName The user input on the selected canteen.
     */
    private void generateListByCanteen(String canteenName) throws CommandException {
        try {
            int canteenSize = ((Number) mapStalls.get(canteenName)).intValue();
            if (canteenSize >= 5) {
                isLessThanFiveOption = false;
                addNewStallToOption(canteenSize, canteenName);
            } else {
                isLessThanFiveOption = true;
                for (int i = 0; i < canteenSize; i++) {
                    JSONObject stall = (JSONObject) stallList.get(i);
                    selectedCanteen = (String) stall.get("canteenName");
                    if (selectedCanteen.equals(canteenName)) {
                        selectedStall = (String) stall.get("name");
                        listOutput.add(new String[]{selectedCanteen, selectedStall});
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CANTEEN_NAME);
        }
    }

    /**
     * This method check if the current option already exist in the list.
     * @param canteen This is the selected canteen
     * @param stall This is the selected stall
     * @return Boolean true if option is not on the list, false otherwise
     */
    private Boolean isNotInList(String canteen, String stall) {
        boolean state = true;
        for (int i = 0; i < listOutput.size(); i++) {
            String[] text = listOutput.get(i);
            if (canteen.equals(text[0]) && stall.equals(text[1])) {
                state = false;
            }
        }
        return state;
    }

    /**
     * This method add the option to the list
     * @param canteenSize The size of the inputted canteen.
     * @param canteenName The name of the canteen.
     */
    private void addNewStallToOption(int canteenSize, String canteenName) {
        while (listOutput.size() < 5) {
            int index = rand.nextInt(canteenSize);
            JSONObject stall = (JSONObject) stallList.get(index);
            selectedCanteen = (String) stall.get("canteenName");
            if (selectedCanteen.equals(canteenName)) {
                selectedStall = (String) stall.get("name");
                if (isNotInList(selectedCanteen, selectedStall)) {
                    listOutput.add(new String[]{selectedCanteen, selectedStall});
                }
            }
        }
    }



}
