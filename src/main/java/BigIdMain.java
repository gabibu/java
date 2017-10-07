import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import textsearch.TextSearchRunner;

public class BigIdMain
{
    private static final String TEXT_FILE_NAME = "big.txt";

    public static void main(String[] args)
    {
        try
        {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "Spring-Module.xml");

            TextSearchRunner textSearchRunner =
                    (TextSearchRunner) context.getBean("textSearchRunner");


            String textFilePath
                    = BigIdMain.class.getClassLoader().getResource(TEXT_FILE_NAME).getPath();

            textSearchRunner.run(textFilePath);

        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
}
