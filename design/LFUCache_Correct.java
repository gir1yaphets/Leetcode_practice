class LFUCache {
    private int cap;
    private Map<Integer, Integer> key2Value; //k-v map
    private Map<Integer, Integer> key2Count; //k-count map
    private Map<Integer, LinkedHashSet<Integer>> count2Key; //count-key map key是个set 记录使用count最小的key
    private int min = -1;
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        key2Value = new HashMap<>();
        key2Count = new HashMap<>();
        count2Key = new HashMap<>();
        
        //初始化count = 1的空set
        count2Key.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!key2Value.containsKey(key)) {
            return -1;
        }
        
        int count = key2Count.get(key);
        key2Count.put(key, count + 1);
        
        //将这个key从key为count的set里删除 加入到以count+1为key的set
        count2Key.get(count).remove(key);
        
        //维护一个min，为了超过capacity的时候remove使用
        //&& count2Key.get(count).size() == 0 表示这个是当前这个count值的最后一个 因为之前会remove掉
        //e.g 比如当前以count=1为key的set里面有{1,2,3},当1remove掉的时候，还有2，3仍然还是1次，这个时候最小值不能++
        //直到2，3都从count=1这里remove后才可以
        if (count == min && count2Key.get(count).size() == 0) {
            min++;
        }
        
        if (!count2Key.containsKey(count + 1)) {
            count2Key.put(count + 1, new LinkedHashSet<>());
        }
        
        count2Key.get(count + 1).add(key);
        
        return key2Value.get(key);
    }
    
    public void put(int key, int value) {
        if (cap <= 0) {
            return;
        }
        
        if (key2Value.containsKey(key)) {
            key2Value.put(key, value);
            get(key);
            return;
        }
        
        if (key2Value.size() >= cap) {
            //remove least frequent one
            int evict = count2Key.get(min).iterator().next();
            count2Key.get(min).remove(evict);
            key2Value.remove(evict);
        } 
        
        key2Value.put(key, value);
        min = 1;
        key2Count.put(key, 1);
        count2Key.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */