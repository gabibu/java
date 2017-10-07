package textsearch;

import textsearch.entities.OccurrenceBlockIndex;
import textsearch.entities.TextOccurrence;
import textsearch.matcher.BlockMatcherAsyncRunner;
import textsearch.matcher.BlockMatcherImpl;
import textsearch.texttoserachproviders.TextToSearchProvider;
import utils.OccurrenceBlockIndexComparator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public class MultiMatcherTextSearchRunner implements TextSearchRunner
{
    private final int concurrency;
    private final int numOfRowsInBlock;
    private TextToSearchProvider textToSearchProvider;

    public MultiMatcherTextSearchRunner(int concurrency,
                                        int numOfRowsInBlock, TextToSearchProvider textToSearchProvider)
    {
        this.concurrency = concurrency;
        this.numOfRowsInBlock = numOfRowsInBlock;
        this.textToSearchProvider = textToSearchProvider;
    }



    public void run(String textFiePath) throws IOException, IllegalAccessException, ExecutionException, InterruptedException, URISyntaxException
    {
        ExecutorService executorService = null;

        try
        {
            List<String> targetText = textToSearchProvider.texts();

            executorService = Executors.newFixedThreadPool(concurrency);

            FileBlockReaderIterator fileBlockReaderIterator
                    = new FileBlockReaderIterator(numOfRowsInBlock, textFiePath);

            List<Future<List<TextOccurrence>>> futures = new LinkedList<>();

            int startingLineIndex = 0;
            while (fileBlockReaderIterator.hasNext())
            {
                List<String> blockLines
                        = fileBlockReaderIterator.next();

                BlockMatcherAsyncRunner blockMatcherAsyncRunner
                        = new BlockMatcherAsyncRunner(targetText, startingLineIndex,
                        blockLines, new BlockMatcherImpl());

                Future<List<TextOccurrence>>
                        blockRunnerFuture = executorService.submit(blockMatcherAsyncRunner);

                futures.add(blockRunnerFuture);

                startingLineIndex += blockLines.size();
            }

            ResultsAggregator resultsAggregator = new ResultsAggregator();

            for(Future<List<TextOccurrence>> future : futures)
            {
                List<TextOccurrence> textOccurrences = future.get();
                resultsAggregator.add(textOccurrences);
            }

            resultsAggregator.paringOccurrences();
        }
        finally
        {
            if(executorService != null)
            {
                executorService.shutdown();
            }
        }
    }
}
