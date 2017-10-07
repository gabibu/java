package textsearch.texttoserachproviders;

import utils.StringHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SimplePatternToSearchProvider implements TextToSearchProvider
{
    private final String fileName;
    public  SimplePatternToSearchProvider(String fileName) {
    this.fileName = fileName;
}

    public List<String> texts() throws URISyntaxException, IOException
    {
        Path p3 = Paths.get(SimplePatternToSearchProvider.class.getClassLoader().getResource(fileName).toURI());

        String fileContent =
                new String(Files.readAllBytes(p3)).trim()
                        .replace("\n", StringHelper.EMPTY).replace("\r",
                        StringHelper.EMPTY);

        return Arrays.asList(fileContent.split(StringHelper.COMMA));
    }


}
