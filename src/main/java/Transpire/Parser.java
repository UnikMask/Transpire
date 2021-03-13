package Transpire;

public class Parser {
    String progLang;
    String countryCode;
    JSONTokener tokener;
    JSONObject json;
    BufferedReader reader;

    public Parser(String countryCode, String progLang) {
        this.progLang = progLang;
        this.countryCode = countryCode;
        try {
            this.reader = new BufferedReader(new FileReader( "trnpkgs" + countryCode + "/" + progLang + ".json"));
        } catch (FileNotFoundException e){
            System.err.println("This language has not been implemented yet.");
        }
        this.tokener = new JSONTokener(this.reader);
        this.json = new JSONObject(this.tokener);
        System.out.println("Id  : " + this.json.getLong("id"));
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
