package textsearch.matcher;

import textsearch.entities.TextOccurrence;
import textsearch.entities.TextOccurrenceInLine;

import java.util.List;

public interface TextSearchMatcher
{
    List<TextOccurrenceInLine> search(String line);
}
