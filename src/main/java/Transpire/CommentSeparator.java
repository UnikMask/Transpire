package Transpire;

import com.github.rwitzel.streamflyer.regex.MatchProcessorResult;
import com.github.rwitzel.streamflyer.regex.MatchProcessor;


import java.util.regex.MatchResult;

public class CommentSeparator implements MatchProcessor {
    @Override
    public MatchProcessorResult process(StringBuilder characterBuffer, int firstModifiableCharacterInBuffer, MatchResult matchResult) {
        characterBuffer.delete(matchResult.start(), matchResult.end());

        return new MatchProcessorResult(matchResult.end() - 5, true);
    }
}
