package textsearch.matcher;

import textsearch.entities.TextOccurrence;

import java.util.List;
import java.util.concurrent.Callable;

public class BlockMatcherAsyncRunner implements Callable<List<TextOccurrence> >
{
    private final List<String> targetText;
    private final int startingLineNumber;
    private final List<String> linesBlock;
    private final BlockMatcher blockMatcher;

    public BlockMatcherAsyncRunner(List<String> targetText, int startingLineNumber,
                                   List<String> linesBlock, BlockMatcher blockMatcher)
    {
        this.targetText = targetText;
        this.startingLineNumber = startingLineNumber;
        this.linesBlock = linesBlock;
        this.blockMatcher = blockMatcher;
    }

    @Override
    public List<TextOccurrence> call() throws Exception
    {
        return blockMatcher.match(linesBlock, targetText, startingLineNumber);
    }
}
