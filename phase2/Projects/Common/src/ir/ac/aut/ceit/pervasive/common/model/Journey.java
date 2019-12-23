

package ir.ac.aut.ceit.pervasive.common.model;

import android.net.Uri;

/**
 * A journey is a sequence of actions which were observed as the user travelled
 * between one known {@link Place} and another.
 *
 * @author Atefeh Zareh
 */
public class Journey {

    public static final String _ID = "_id";
    public static final String START = "start";
    public static final String END = "end";
    public static final String STEPS = "steps";
    public static final String NUMBER = "number";

    public static final Uri CONTENT_URI = Uri.parse("content://ir.ac.aut.ceit.pervasive.contextanalyser.journeyscontentprovider/journeys");
    public static final String CONTENT_TYPE = "vnd.contextanalyser.journey";

    private final long id;
    private final long start;
    private final long end;
    private final int steps;
    private final int number;

    public Journey(final long id, final long start, final long end,
            final int steps, final int number) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.steps = steps;
        this.number = number;
    }

    public long getEnd() {
        return end;
    }

    public long getId() {
        return id;
    }

    public long getStart() {
        return start;
    }

    public int getSteps() {
        return steps;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Journey{" + "id=" + id + " start=" + start + " end=" + end
                + " steps=" + steps + " number=" + number + '}';
    }

}
