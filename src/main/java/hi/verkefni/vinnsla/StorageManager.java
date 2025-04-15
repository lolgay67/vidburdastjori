package hi.verkefni.vinnsla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Calendar;

public class StorageManager {
    private HashMap<String, ArrayList<Object>> storage = new HashMap<String, ArrayList<Object>>();

    public void store(ArrayList<Object> objects) {
        storage.put(String.valueOf(objects.get(0)), objects);
    }

    public ArrayList<Object> getStored(String name) {
        return storage.get(name);
    }

    public Object[] getKeys() {
        return storage.keySet().toArray();
    }

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

    public void removeStored(String name) {
        storage.remove(name);
    }
}
