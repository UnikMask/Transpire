package Transpire;


import com.github.rwitzel.streamflyer.regex.MatchProcessor;
import com.github.rwitzel.streamflyer.regex.MatchProcessorResult;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class SpaceSeparator implements MatchProcessor {
    @Override
    public MatchProcessorResult process(StringBuilder characterBuffer, int firstModifiableCharacterInBuffer, MatchResult matchResult) {
        characterBuffer.insert(matchResult.end(), " " + characterBuffer.substring(matchResult.start(), matchResult.end()) + " ");
        characterBuffer.delete(matchResult.start(), matchResult.end());

        return new MatchProcessorResult(matchResult.end()+1, true);
    }
}
