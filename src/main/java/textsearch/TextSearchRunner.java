package textsearch;

import textsearch.entities.TextOccurrence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TextSearchRunner
{
   void run(String filePath) throws IOException, IllegalAccessException, ExecutionException, InterruptedException, URISyntaxException;
}
