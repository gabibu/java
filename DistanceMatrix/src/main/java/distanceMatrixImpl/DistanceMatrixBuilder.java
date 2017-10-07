package distanceMatrixImpl;

import entities.Location;
import entities.LocationsPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistanceMatrixBuilder
{
    private final double matrix[][];
    private Map<Location, Integer> locationToIndex;

    public DistanceMatrixBuilder(List<Location> locations)
    {
        matrix = new double[locations.size()][locations.size()];
        locationToIndex = new HashMap<>(locations.size());

        int index = 0;
        for(Location location : locations)
        {
            locationToIndex.put(location, index);
            index++;
        }
    }

    public void addValues(Map<LocationsPair, Double> locationsDistance)
    {
        for(Map.Entry<LocationsPair, Double>  locationsDistancesEntry : locationsDistance.entrySet())
        {
            LocationsPair locationsPair = locationsDistancesEntry.getKey();
            int index1 = locationToIndex.get(locationsPair.getLocation1());
            int index2 = locationToIndex.get(locationsPair.getLocation2());
            double distance = locationsDistancesEntry.getValue();

            matrix[index1][index2] = distance;
            matrix[index2][index1] = distance;
        }
    }

    public double [][] matrix()
    {
        return this.matrix;
    }

}
