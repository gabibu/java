package textsearch.texttoserachproviders;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

public interface TextToSearchProvider
{
    List<String> texts() throws URISyntaxException, IOException;
}
