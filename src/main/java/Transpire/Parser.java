package Transpire;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import com.github.rwitzel.streamflyer.core.Modifier;
import com.github.rwitzel.streamflyer.core.ModifyingReader;
import org.apache.commons.io.IOUtils;
import com.github.rwitzel.streamflyer.regex.RegexModifier;


public class Parser {
    String progLang;
    String countryCode;
    Translations translator;
    Mapper mapper;
    Map<String, String> quoteMap = new HashMap<>();
    String commentRegexes;


    public Parser(String countryCode, String progLang) throws NotSupportedLanguage {
        this.progLang = progLang;
        this.countryCode = countryCode;
        try {
            this.translator = new Translations(this.countryCode, this.progLang, "translations");
            this.mapper = this.translator.getMapper();
            this.commentRegexes = this.translator.getCommentRegex();
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
    public String parseString(String input) throws IOException {
        String[] commentRegex = this.commentRegexes.split(" ");
        for (String regex: commentRegex) {
            input = removeComments(input, regex);
        }
        input = replaceQuotes(input);
        input = replaceSingleQuotes(input);

        Map<String, String> variableMap = new HashMap<>();

        input = input.replaceAll(" {4}", "\t");
        input = input.replaceAll("\n", "\n ");

        Reader baseReader = new StringReader(input);
        //String regex = ("(\\|{2}|&{2}|\\*{2}|!=|->|:{2}|\\-{2}|={2}|>=|;|:|\\{|}\\+|-|\\*|/|\\[|\\]|>|<|=|\\(|\\)|\\.|,|&
        String regex = "(\\/{2}=|" + "\\*{2}=|" + "\\>{2}=|" + "\\<{2}=|" + "\\|{2}|" + "&{2}|" + "\\*{2}|" + "!=|"+
                "->|" + ":{2}|" + "\\+{2}|" + "\\-{2}|" + "={2}|" + ">=|" + "<=|" + "\\+=|" + "\\-=|" + "\\*=|" + "\\/=|" + "\\%=|" +
                "&=|" + "\\|=|" + "\\^=|" + "\\>{2}|" + "\\<{2}|" + ";|" + ":|" + "\\{|" + "\\}|" + "\\+|" + "\\-|" + "\\*|" + "\\/|" + "\\[|" + "\\]|" + ">|" + "<|" +
                "=|" + "\\(|" + "\\)|" + "\\.|" + ",|" + "&|" + "\\||" + "%|" + "^|" + "~" + ")";


        Modifier modifier = new RegexModifier(regex, Pattern.MULTILINE, new SpaceSeparator(), 0 ,2048);
        Reader modifyingReader = new ModifyingReader(baseReader, modifier);

        String toTokenize = IOUtils.toString(modifyingReader);
        List<String> tokens = new ArrayList<>(Arrays.asList(toTokenize.split(" ")));

        tokens.removeAll(Collections.singletonList(""));

        for (int i = 0; i < tokens.size(); i++) {
            if(mapper.checkIfKeyword(tokens.get(i).trim())){
                String replace = returnTabsAndSpaces(tokens, i);
                tokens.set(i, replace + mapper.translate(tokens.get(i).trim()));
            }

            if(i < tokens.size()-1 && tokens.get(i + 1).equals("=")) {
                String replace = returnTabsAndSpaces(tokens, i);
                String newName = generateVarName();
                variableMap . put(tokens.get(i), newName);
                tokens.set(i, replace + newName);
            }

            if(variableMap.get(tokens.get(i).trim()) != null) {
                String replace = returnTabsAndSpaces(tokens, i);
                tokens.set(i, replace + variableMap.get(tokens.get(i).trim()));
            }

            if(this.quoteMap.get(tokens.get(i).trim()) != null) {
                tokens.set(i, this.quoteMap.get(tokens.get(i).trim()));
            }
        }

        String result = "";
        for (String token : tokens) {
            if (!result.equals("") && result.charAt(result.length() - 1) == '\n') {
                result = result + token;
            } else if (result.equals("")){
                result = token;
            } else {
                result = result + " " + token;
            }
        }

        return result;
    }

    private String replaceQuotes(String input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\"");

        // starts from first quote instead of start of file
        if (scanner.hasNext()) {
            scanner.next();
        }

        while(scanner.hasNext()) {
            String quote = "\"" + scanner.next() + "\"";
            String quoteName = generateQuoteName();
            quoteMap.put(quoteName, quote);
            input = input.replace(quote, quoteName);
            if (scanner.hasNext()) {
                scanner.next();
            }
        }
        return input;
    }

    private String replaceSingleQuotes(String input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("'");

        // starts from first quote instead of start of file
        if (scanner.hasNext()) {
            scanner.next();
        }

        while(scanner.hasNext()) {
            String quote = "'" + scanner.next() + "'";
            String quoteName = generateQuoteName();
            quoteMap.put(quoteName, quote);
            input = input.replace(quote, quoteName);
            if (scanner.hasNext()) {
                scanner.next();
            }
        }
        return input;
    }

    private String returnTabsAndSpaces(List<String> tokens, int i) {
        String replace = "";
        for(int j = 0; j < tokens.get(i).length(); j++){
            if(tokens.get(i).charAt(j) == '\n'){
                replace = replace + '\n';
            }
            if(tokens.get(i).charAt(j) == '\t'){
                replace = replace + '\t';
            }
        }
        return replace;
    }

    public String removeComments(String input, String regex) throws IOException{
        Reader commentReader = new StringReader(input);
        Modifier modifier = new RegexModifier(regex, Pattern.MULTILINE , new CommentSeparator(), 0 ,2048);
        ModifyingReader modifyingReader = new ModifyingReader(commentReader, modifier);
        return IOUtils.toString(modifyingReader);
    }

    public String generateVarName() {
        return "var" + System.nanoTime();
    }

    public String generateQuoteName() {
        return "quote" + System.nanoTime();
    }
}
