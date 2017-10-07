package textsearch.entities;

public class TextOccurrence
{
    private final String text;
    private final OccurrenceBlockIndex occurrenceBlockIndex;

    public TextOccurrence(String text, int lineOffset, int charOffset) {
        this.text = text;
        occurrenceBlockIndex = new OccurrenceBlockIndex(lineOffset, charOffset);
    }

    public String getText() {
        return text;
    }



    public OccurrenceBlockIndex getOccurrenceBlockIndex() {
        return occurrenceBlockIndex;
    }


    @Override
    public String toString() {
        return "TextOccurrence{" +
                "text='" + text + '\'' +
                ", occurrenceBlockIndex=" + occurrenceBlockIndex +
                '}';
    }
}
