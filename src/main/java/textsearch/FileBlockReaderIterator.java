package textsearch;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileBlockReaderIterator implements Iterator<List<String>>
{
    private final int numOfLinesInBlock;
    private Scanner fileScanner;

    public FileBlockReaderIterator(int numOfLinesInBlock, String filePath) throws IllegalAccessException, FileNotFoundException
    {
        this.numOfLinesInBlock = numOfLinesInBlock;

        if(filePath == null || filePath.length() == 0)
        {
            throw new IllegalAccessException("file path cant be null or empty");
        }

        fileScanner = new Scanner(new File(filePath));
    }

    public boolean hasNext()
    {
        if(fileScanner == null)
        {
            return false;
        }
        else if(fileScanner.hasNextLine())
        {
            return true;
        }
        else
        {
            if(fileScanner != null)
            {
                fileScanner.close();
                fileScanner = null;
            }

            return false;
        }
    }

    public List<String> next()
    {
        List<String> lines = new ArrayList<String>(numOfLinesInBlock);

        int counter = 0;

        while(fileScanner.hasNextLine() && counter < numOfLinesInBlock)
        {
            lines.add(fileScanner.nextLine());
            counter++;
        }

        return lines;
    }

    public void remove()
    {
        throw new NotImplementedException();
    }

}
