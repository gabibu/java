package entities;

public class LocationsPair
{
    private final Location location1;
    private final Location location2;

    public LocationsPair(Location location1, Location location2) {
        this.location1 = location1;
        this.location2 = location2;
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationsPair that = (LocationsPair) o;

        if (location1 != null ? !location1.equals(that.location1) : that.location1 != null) return false;
        return location2 != null ? location2.equals(that.location2) : that.location2 == null;
    }

    @Override
    public int hashCode() {
        int result = location1 != null ? location1.hashCode() : 0;
        result = 31 * result + (location2 != null ? location2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LocationsPair{" +
                "location1=" + location1 +
                ", location2=" + location2 +
                '}';
    }
}
