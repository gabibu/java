import distanceMatrixImpl.DistanceMatrixRunner;
import distanceMatrixImpl.LocationProvider;
import entities.Location;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class DistanceMatrixMain
{
    public static void main(String[] args) throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "Spring-Module.xml");

        DistanceMatrixRunner textSearchRunner =
                (DistanceMatrixRunner) context.getBean("distanceMatrixRunner");

        LocationProvider locationsProvider =
                (LocationProvider) context.getBean("locationsProvider");

        List<Location> locations = locationsProvider.locations();
        double [][] distanceMatrix = textSearchRunner.calcDistanceMatrix(locations);


        System.out.println("finish computing distance matrix for " + locations.size() + " locations");

    }
}
