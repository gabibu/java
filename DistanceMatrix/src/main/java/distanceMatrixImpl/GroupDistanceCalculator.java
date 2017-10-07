package distanceMatrixImpl;

import entities.Location;
import entities.LocationsPair;
import utils.DistanceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class GroupDistanceCalculator implements Callable<Map<LocationsPair, Double>>
{
    private final List<Location> locations;
    private final Location baseLocation;

    public GroupDistanceCalculator(List<Location> locations, Location baseLocation) {
        this.locations = locations;
        this.baseLocation = baseLocation;
    }

    public  Map<LocationsPair, Double> call() throws Exception
    {
        Map<LocationsPair, Double> indexPairDistance = new HashMap<>();

        indexPairDistance.put( new LocationsPair(baseLocation, baseLocation), 0.0);

        for(Location location : locations)
        {
            double distance = DistanceUtils.euclideanDistance(baseLocation, location);

            LocationsPair locationsPair = new LocationsPair(baseLocation, location);

            indexPairDistance.put(locationsPair, distance);
        }

        return indexPairDistance;
    }
}
