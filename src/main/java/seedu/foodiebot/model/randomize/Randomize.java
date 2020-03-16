package seedu.foodiebot.model.randomize;

import java.io.FileReader;
import java.io.IOException;
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
    private String output = "";

    private Random rand = new Random();

    public Randomize(){
    }

    /**
     * This method return the name of the selected canteen.
     * @return String of the canteen name.
     */
    public String selectCanteen() {
        return selectedCanteen;
    }

    /**
     * This method return the name of the selected stall.
     * @return String of the stall name
     */
    public String selectStall() {
        return selectedStall;
    }

    /**
     * This method return the output list option.
     * @return String of options.
     */
    public String output() {
        return output;
    }
    /**
     * This function select a canteen from the jsonfile (foodiebot.json).
     * @param file which is provided from the RandomizeCommand
     */
    public void getSelectedCanteen(FileReader file) {
        int listSize;
        int index;
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            JSONArray canteenList = (JSONArray) obj.get("canteens");
            //System.out.println((canteenList));

            listSize = canteenList.size();
            index = rand.nextInt(listSize);

            JSONObject canteen = (JSONObject) canteenList.get(index);
            selectedCanteen = (String) canteen.get("name");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method produce a list of options from the jsonfile (foodiebot-stalls.json).
     * @param file which is provided from the RandomizeCommand.
     */
    public void getOptions(FileReader file) throws CommandException {
        int listSize;
        int index;
        int i = 0;
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(file);
            JSONArray stallList = (JSONArray) obj.get("stalls");
            //System.out.println((canteenList));

            listSize = stallList.size();

            while (i < 5) {
                index = rand.nextInt(listSize);
                JSONObject stall = (JSONObject) stallList.get(index);
                selectedStall = (String) stall.get("name");
                selectedCanteen = (String) stall.get("canteenName");
                if (!output.contains(selectedStall)) {
                    output = output + String.format("%d. %s: %s\n", i + 1, selectedCanteen, selectedStall);
                    i++;
                }
            }
        } catch (IOException | ParseException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_FILEREADER);
        }
    }



}
