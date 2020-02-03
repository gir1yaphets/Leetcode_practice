import java.util.*;
class LFUCache {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.get(1);
        lfuCache.put(3, 3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4, 4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }

    class Pair {
        int value;
        int times;
        int index;
        
        public Pair() {
            this.value = 0;
            this.times = 0;
            this.index = 0;
        }
        
        public Pair(int v, int t, int idx) {
            this.value = v;
            this.times = t;
            this.index = idx;
        }
    }
    
    private int cap;
    private int index;
    private Map<Integer, Pair> map;
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Pair pair = map.get(key);
        pair.times += 1;
        pair.index = index++;
        map.put(key, pair);
        return map.get(key).value;
    }
    
    public void put(int key, int value) {
        if (cap == 0) {
            return;
        }
        
        if (map.containsKey(key)) {
            Pair pair = map.get(key);
            pair.value = value;
            pair.times += 1;
            pair.index = index++;
            map.put(key, pair);
            return;
        }
        
        int minTime = Integer.MAX_VALUE;
        int minKey = 0;
        List<Map.Entry<Integer, Pair>> list = new ArrayList<>();
        int minIndex = Integer.MAX_VALUE;
        if (map.size() == cap) {
            //remove least frequent one
            for (Map.Entry<Integer, Pair> entry : map.entrySet()) {
                minTime = Math.min(minTime, entry.getValue().times);
            }
            
            for (Map.Entry<Integer, Pair> entry : map.entrySet()) {
                Pair p = entry.getValue();
                if (p.times == minTime) {
                    list.add(entry);
                }
            }
            
            if (list.size() == 1) {
                minKey = list.get(0).getKey();
            } else {
                Collections.sort(list, (o1, o2) -> o1.getValue().index - o2.getValue().index);
                
                // for (int i = 0; i < list.size(); i++) {
                //     System.out.println(list.get(i).getKey());
                // }
                minKey = list.get(0).getKey();
            }
            
            // System.out.println("minKey = " + minKey);
            map.remove(minKey);
        } 
        
        map.put(key, new Pair(value, 0, index));
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */