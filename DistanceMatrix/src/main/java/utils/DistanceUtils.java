package utils;

import entities.Location;

public abstract class DistanceUtils
{
    public static double euclideanDistance(Location location1, Location location2)
    {
       return Math.sqrt(Math.pow(location1.getLon() - location2.getLon(), 2) +
                Math.pow(location1.getLat() - location2.getLat(), 2));
    }
}
