package hi.verkefni.vinnsla;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import hi.verkefni.vinnsla.Flokkur;

public class EventModel {
    private Object[][] objects = new Object[8][5];
    int i = 0;
    /**
     * Empty Constructor
     */
    EventModel() {
    };
    /**
     * 
     * @param name name of the Event to get
     * @return an array that represents the event
     */
    public Object[] getProp(String name) {
        /*
         * Object[] outObjects = new Object[5];
         * int iter = getKey(name);
         * for (int j = 0; j < 5; j++) {
         * outObjects[j] = objects[iter][j];
         * }
         */
        return objects[getKey(name)];
    }
    /**
     * Get all event names
     * @return A string array with the names of all events.
     */
    public String[] getProperties() {
        String[] strings = new String[i];
        int stringnext = 0;
        for (int j = 0; j < i; j++) {
            if (objects[j][0] != null) {
                strings[stringnext] = "" + objects[j][0];
                stringnext++;
            }

        }
        return strings;

    }
    /**
     * function to add a event to the list of events
     * @param nameIn The name of the event
     * @param flokkurIn The category of the event
     * @param dagsetningIn The date of the event
     * @param timasetningIn The time of the event
     * @param kynningarmyndband A demonstration video for the event
     */
    public void addProp(String nameIn, String flokkurIn, String dagsetningIn, String timasetningIn,
            Object kynningarmyndband) {
        Object[] inobjects = new Object[5];
        inobjects[0] = nameIn;
        inobjects[1] = flokkurIn;
        inobjects[2] = dagsetningIn;
        inobjects[3] = timasetningIn;
        inobjects[4] = kynningarmyndband;
        if (getKey(nameIn) != -1) {
            int key = getKey(nameIn);
            for (int j = 0; j < 5; j++) {
                objects[key][j] = inobjects[j];
            }
        }
        if (i == objects.length) {
            Object[][] tempObjects = objects.clone();
            objects = new Object[(i * 2)][5];
            for (int j = 0; j < tempObjects.length; j++) {
                for (int k = 0; k < 5; k++) {
                    objects[j][k] = tempObjects[j][k];
                }
            }

        }
        objects[i] = inobjects;
        i += 1;
    }
    /**
     * private function that fetches the index of a element based of its name.
     * @param name name of event
     * @return index representing the event
     */
    private int getKey(String name) {
        for (int j = 0; j < i; j++) {
            if (objects[j] != null) {
                if (objects[j][0] == name) {
                    return j;
                }
            }
        }
        return -1;
    }

}
