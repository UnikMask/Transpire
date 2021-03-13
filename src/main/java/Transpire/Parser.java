package Transpire;

import org.json.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Parser {
    String progLang;
    String countryCode;

    public Parser(String countryCode, String progLang) {
        this.progLang = progLang;
        this.countryCode = countryCode;
    }

    /**
     * Replaces all instances of keywords with english equivalent
     * @param input the contents of the file as a string
     * @return the contents of the file as string with all keywords replaced with english equivalent
     */
    public String parseString(String input) {
        String[] tokens = input.split(" ");
        // get json file for this.progLang

        return input;
    }

    public boolean checkIfKeyword(String word){
        return false;
    }
    
}
