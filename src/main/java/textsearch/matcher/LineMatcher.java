package textsearch.matcher;

import textsearch.entities.TextOccurrenceInLine;
import utils.StringHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineMatcher implements TextSearchMatcher
{
    private final Pattern nameInSentencePatter;
    private final Pattern exactNameMatch;

    public LineMatcher(List<String> targetText) throws IOException, URISyntaxException
    {
        this.nameInSentencePatter =firstNameInSentencedPattern(targetText);
        this.exactNameMatch = exactMatch(targetText);
    }


    public List<TextOccurrenceInLine> search(String line)
    {
        Matcher match = nameInSentencePatter.matcher(line);
        List<TextOccurrenceInLine> occurrences = new LinkedList<>();

        while (match.find())
        {
            int matchStart = match.start();
            String matchedText = line.substring(matchStart, match.end());

            Matcher  exactMath = exactNameMatch.matcher(matchedText);
            exactMath.find();

            matchStart += exactMath.start();
            String exactString = matchedText.substring(exactMath.start(), exactMath.end());
            occurrences.add(new TextOccurrenceInLine(exactString, matchStart));
        }

        return occurrences;
    }

    private Pattern exactMatch(List<String> targetText ) throws IOException, URISyntaxException
    {
        StringBuilder builder = new StringBuilder();
        for(String text : targetText)
        {
            if(builder.length() > 0)
            {
                builder.append(StringHelper.PIPE);
            }

            builder.append(text);
        }

        Pattern pattern = Pattern.compile(builder.toString());
        return pattern;
    }

    private Pattern firstNameInSentencedPattern(List<String> targetText ) throws IOException, URISyntaxException
    {
        StringBuilder builder = new StringBuilder();

        for(String text : targetText)
        {
            if(builder.length() > 0)
            {
                builder.append(StringHelper.PIPE);
            }

            builder.append(text);
        }


        String str = String.format("(?:^|\\W)(%s)(?:$|\\W)", builder.toString());
        Pattern pattern1 = Pattern.compile(str);
        return pattern1;
    }
}
