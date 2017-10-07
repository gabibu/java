package textsearch;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import textsearch.entities.OccurrenceBlockIndex;
import textsearch.entities.TextOccurrence;
import utils.OccurrenceBlockIndexComparator;
import utils.StringHelper;

import java.util.*;

public class ResultsAggregator
{
    private Map<String, List<OccurrenceBlockIndex>> textToOccurrenceIndexes;

    public ResultsAggregator()
    {
        textToOccurrenceIndexes = new HashMap<>();
    }

    public void add(List<TextOccurrence> textOccurrences)
    {
        for(TextOccurrence textOccurrence : textOccurrences)
        {
            List<OccurrenceBlockIndex> currentTextOccurrences
                    = textToOccurrenceIndexes.get(textOccurrence.getText());

            if(currentTextOccurrences == null)
            {
                currentTextOccurrences = new LinkedList<>();
                textToOccurrenceIndexes.put(textOccurrence.getText(), currentTextOccurrences);
            }

            currentTextOccurrences.add(textOccurrence.getOccurrenceBlockIndex());
        }
    }

    public void paringOccurrences()
    {
        Map<String, List<OccurrenceBlockIndex>> textOccurrences
                = this.textToOccurrenceIndexes;

        for(Map.Entry<String, List<OccurrenceBlockIndex>> textToOccurrencesEntry :
                textOccurrences.entrySet())
        {
            String text = textToOccurrencesEntry.getKey();
            List<OccurrenceBlockIndex> occurrenceBlockIndices = textToOccurrencesEntry.getValue();

            Collections.sort(occurrenceBlockIndices,
                    new OccurrenceBlockIndexComparator());


            StringBuilder textIndices = new StringBuilder();


            for(OccurrenceBlockIndex occurrenceBlockIndex : occurrenceBlockIndices)
            {
                if(textIndices.length() > 0)
                {
                    textIndices.append(StringHelper.COMMA);
                }
                else
                {
                    textIndices.append(text);
                    textIndices.append(" --> [");
                }

                textIndices.append(occurrenceBlockIndex.toString());
            }

            textIndices.append("]");
            System.out.println(textIndices.toString());
        }
    }

}
