package distanceMatrixImpl;

import entities.Location;

import java.util.List;

public interface LocationProvider
{
    List<Location> locations() throws Exception;
}
