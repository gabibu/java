package distanceMatrixImpl;

import entities.Location;
import entities.LocationsPair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncDistanceMatrixRunner implements DistanceMatrixRunner
{
    private final int concurrency;

    public AsyncDistanceMatrixRunner(int concurrency) {
        this.concurrency = concurrency;
    }


    public double [][]  calcDistanceMatrix(List<Location> locations) throws Exception
    {
       ExecutorService executorService = null;

       try
       {
           DistanceMatrixBuilder distanceMatrixBuilder = new DistanceMatrixBuilder(locations);

           executorService = Executors.newFixedThreadPool(concurrency);
           List<Future<Map<LocationsPair, Double>>> futures = new LinkedList<>();

           for(int i = 0; i < locations.size(); i++)
           {
               Location baseLocation = locations.get(i);
               List<Location> otherLocations = locations.subList(i + 1, locations.size());

               GroupDistanceCalculator groupDistanceCalculator
                       = new GroupDistanceCalculator(otherLocations, baseLocation);

               Future<Map<LocationsPair, Double>> distancesFuture
                       = executorService.submit(groupDistanceCalculator);

               futures.add(distancesFuture);
           }

           for(Future<Map<LocationsPair, Double>> future : futures)
           {
               Map<LocationsPair, Double> distances  = future.get();
               distanceMatrixBuilder.addValues(distances);
           }

           return distanceMatrixBuilder.matrix();
       }
       catch (Exception e)
       {
           System.out.println(e);
           throw e;
       }
       finally {
           if(executorService != null)
           {
               executorService.shutdown();
           }
       }
    }
}
