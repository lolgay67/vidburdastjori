package hi.verkefni.vinnsla;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageManager {
    HashMap<String, ArrayList<Object>> storage = new HashMap<String, ArrayList<Object>>();
    public void store(ArrayList<Object> objects){
        storage.put(String.valueOf(objects.get(0)),objects);
    }
    public ArrayList<Object> getStored(String name){
        return storage.get(name);
    }
    public Object[] getKeys(){
        return storage.keySet().toArray();
    }
}
