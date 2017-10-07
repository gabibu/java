package distanceMatrixImpl;

import entities.Location;

import java.util.List;

public interface DistanceMatrixRunner
{
    double [][]  calcDistanceMatrix(List<Location> locations) throws Exception;
}


