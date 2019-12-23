
package ir.ac.aut.ceit.pervasive.common.model;

import android.net.Uri;

/**
 * A place is a named location which has some significance for the user. Most
 * places come about when the user remains stationary for a period of time.
 * 
 * @author Atefeh Zareh
 */
public class Place {

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";
    public static final String DURATION = "duration";
    public static final String VISIT_COUNT = "times";
    public static final String LAST_VISIT = "lastvisit";

    public static final Uri CONTENT_URI = Uri.parse("content://ir.ac.aut.ceit.pervasive.contextanalyser.placescontentprovider/places");
    public static final String CONTENT_TYPE = "vnd.contextanalyser.location";

    private final long id;
    private final String name;
    private final double lat;
    private final double lon;

    public Place(final long id, final String name, final double lat, final double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Place{id=" + id + " name=" + name + " lat=" + lat + " lon=" + lon + '}';
    }
}
