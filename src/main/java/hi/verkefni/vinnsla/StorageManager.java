package hi.verkefni.vinnsla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;

public class StorageManager {
    private HashMap<String, ArrayList<Object>> storage = new HashMap<String, ArrayList<Object>>();

    /**
     * Stores objects from given array in self
     * @param objects objects to store
     */
    public void store(ArrayList<Object> objects) {
        storage.put(String.valueOf(objects.get(0)), objects);
    }

    /**
     * Gets objects based on string name in first field of stored object
     * @param name name of object to get
     * @return arraylist representation of objects stored
     */
    public ArrayList<Object> getStored(String name) {
        return storage.get(name);
    }

    /**
     * Getter for all keys/names for getting objects
     * @return Array representation of keys.
     */
    public Object[] getKeys() {
        return storage.keySet().toArray();
    }

    /**
     * Turns all objects into Calendar to string hashmaps so that finding stored objects based on date is easy
     * @return a calendar-string hashmap of dates-names
     */
    public HashMap<Calendar, String> getDateName() {
        HashMap<Calendar, String> dateName = new HashMap<Calendar, String>();
        for (Object names : getKeys()) {
            ArrayList<Object> tempObjects = storage.get(String.valueOf(names));
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set(
                    Integer.valueOf(String.valueOf(tempObjects.get(1))),
                    Integer.valueOf(String.valueOf(tempObjects.get(2))),
                    Integer.valueOf(String.valueOf(tempObjects.get(3))));
            dateName.put(tempCalendar, String.valueOf(names));
        }
        return dateName;
    }
    /**
     * removes a stored object based off name
     * @param name name of object to remove
     */
    public void removeStored(String name) {
        storage.remove(name);
    }
}
