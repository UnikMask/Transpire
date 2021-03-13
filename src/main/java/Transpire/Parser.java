package Transpire;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.HashMap;
import org.json.*;


public class Parser {
    String progLang;
    String countryCode;
    Translations translator;

    public Parser(String countryCode, String progLang) throws NotSupportedLanguage {
        this.progLang = progLang;
        this.countryCode = countryCode;
        try {
            this.translator = new Translations(this.countryCode, this.progLang, "trnpkgs");
        } catch (NotSupportedLanguage e){
            throw new NotSupportedLanguage("Unsupported Language");
        }
    }


    //(\|{2}|&{2}|!=|={2}|>=|;|:|\{|}|\+|-|\*|/|\[|\]|>|<|=)


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

    public String returnKeyword(String word){
        return "";
    }

}
