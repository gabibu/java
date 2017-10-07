package textsearch.matcher;

import textsearch.entities.TextOccurrence;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

public interface BlockMatcher
{
    List<TextOccurrence> match(List<String> block, List<String> targetText, int startingLineIndex) throws IOException, URISyntaxException;
}
