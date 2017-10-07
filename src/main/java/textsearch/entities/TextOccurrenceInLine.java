package textsearch.entities;

public class TextOccurrenceInLine
{
    private final String text;
    private final int charOffset;

    public TextOccurrenceInLine(String text, int charOffset) {
        this.text = text;
        this.charOffset = charOffset;
    }

    public String getText() {
        return text;
    }

    public int getCharOffset() {
        return charOffset;
    }

    @Override
    public String toString() {
        return "TextOccurrenceInLine{" +
                "text='" + text + '\'' +
                ", charOffset=" + charOffset +
                '}';
    }
}
