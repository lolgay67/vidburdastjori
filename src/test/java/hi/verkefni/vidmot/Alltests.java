package hi.verkefni.vidmot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

import hi.verkefni.vinnsla.StorageManager;
public class Alltests {
    StorageManager storeMan;
    @Before
    public void setup(){
        storeMan = new StorageManager();
    }

    @Test
    public void testStoreAndRetrieve(){
        ArrayList<Object> objects = new ArrayList<Object>(); 
        objects.add("EventName");
        objects.add(1234);
        objects.add(1);
        objects.add(12);
        objects.add("This is a description");
        objects.add("#color");
        storeMan.store(objects);
        objects.set(0, "Event2");
        storeMan.store(objects);
        Object[] keyTest = storeMan.getKeys(); // testing the get keys function.
        assertEquals(keyTest.length, 2);
        for (Object object : keyTest) {
            // testing whether all elements in the array are either Event2 or EventName
            assertTrue(!(object == "Event2"||object=="EventName"));
        }
        ArrayList<Object> secondEvent = storeMan.getStored("Event2"); // testing the get stored function.
        for (int i = 0; i < secondEvent.size(); i++) {
            assertEquals(secondEvent.get(i), objects.get(i));
        }
        assertEquals(0, 1);
    }
    
}
