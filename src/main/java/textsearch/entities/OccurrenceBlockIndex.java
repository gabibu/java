package textsearch.entities;

public class OccurrenceBlockIndex
{
    private int lineOffset;
    private int charOffset;

    public OccurrenceBlockIndex(int lineOffset, int charOffset) {
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    public int getLineOffset() {
        return lineOffset;
    }

    public int getCharOffset() {
        return charOffset;
    }

    @Override
    public String toString() {
        return  "[lineOffset=" + lineOffset +
                ", charOffset=" + charOffset + ']';
    }
}
