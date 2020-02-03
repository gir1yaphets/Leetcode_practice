import java.util.*;

class TimeMap {
    /** Initialize your data structure here. */
    private Map<String, TreeMap<Integer, String>> map;
    
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> tm = map.getOrDefault(key, new TreeMap<>());
        tm.put(timestamp, value);
        map.put(key, tm);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        
        TreeMap<Integer, String> tm = map.get(key);
        
        Integer floor = tm.floorKey(timestamp);
        
        if (floor == null) {
            return "";
        }
        
        return tm.get(floor);
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */