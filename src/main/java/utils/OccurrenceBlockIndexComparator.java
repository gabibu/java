package utils;

import textsearch.entities.OccurrenceBlockIndex;

import java.util.Comparator;

public class OccurrenceBlockIndexComparator implements Comparator<OccurrenceBlockIndex>
{

    @Override
    public int compare(OccurrenceBlockIndex o1, OccurrenceBlockIndex o2) {

        int compareRes
                = Integer.compare(o1.getLineOffset(), o2.getLineOffset());

        if(compareRes == 0)
        {
            compareRes
                    = Integer.compare(o1.getCharOffset(), o2.getCharOffset());
        }

        return compareRes;
    }
}
