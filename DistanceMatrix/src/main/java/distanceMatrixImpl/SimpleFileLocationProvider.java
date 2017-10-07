package distanceMatrixImpl;

import entities.Location;
import utils.StringHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class SimpleFileLocationProvider implements LocationProvider
{

    @Override
    public List<Location> locations() throws Exception
    {
        String locationsFile
                = SimpleFileLocationProvider.class.getClassLoader().getResource("locations.csv").getPath();

        List<Location> locations = new LinkedList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(locationsFile)))
        {
            String header = br.readLine();
            System.out.println(StringHelper.HEADER + header);

            for(String line; (line = br.readLine()) != null; )
            {
                String[] latLon = line.split(StringHelper.COMMA);
                double lat = Double.parseDouble(latLon[0]);
                double lon = Double.parseDouble(latLon[1]);
                locations.add(new Location(lat, lon));
            }
        }

        return locations;
    }
}
