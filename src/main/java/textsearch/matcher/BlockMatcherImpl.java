package textsearch.matcher;

import textsearch.entities.TextOccurrence;
import textsearch.entities.TextOccurrenceInLine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class BlockMatcherImpl implements BlockMatcher
{
    @Override
    public List<TextOccurrence> match(List<String> blockLines, List<String> targetText,
                                      int startingLineIndex) throws IOException, URISyntaxException {
        List<TextOccurrence> textOccurrences = new LinkedList<>();

        LineMatcher lineMatcher = new LineMatcher(targetText);
        int lineIndex= startingLineIndex;
        for(String line : blockLines)
        {
            List<TextOccurrenceInLine> lineTextOccurrences
                    =  lineMatcher.search(line);

            for(TextOccurrenceInLine textOccurrence : lineTextOccurrences)
            {
                textOccurrences.add(new TextOccurrence(textOccurrence.getText(),
                        lineIndex, textOccurrence.getCharOffset()));
            }

            lineIndex++;
        }

        return textOccurrences;
    }
}
