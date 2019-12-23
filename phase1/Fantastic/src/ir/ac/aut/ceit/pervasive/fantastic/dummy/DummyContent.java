package ir.ac.aut.ceit.pervasive.fantastic.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<SensorItem> ITEMS = new ArrayList<SensorItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, SensorItem> ITEM_MAP = new HashMap<String, SensorItem>();

    static {
        // Add 3 sample items.
        addItem(new SensorItem("1", "Show My Location"));
    	addItem(new SensorItem("2", "Show Other Sensors"));
        addItem(new SensorItem("3", "Help"));
        addItem(new SensorItem("4", "About Us"));
        addItem(new SensorItem("5", "Contact Us"));
        
    }

    private static void addItem(SensorItem item) {
//    	SensorFacade facade = new SensorFacade();
//    	facade.getAllAvailableSensors();
//    	
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * represent a sensor in android device
     */
    public static class SensorItem {
        public String id;
        public String name;

        public SensorItem(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
