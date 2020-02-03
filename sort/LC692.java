import java.util.*;
class LC692 {
    class Pair {
        String word;
        int count;
        
        public Pair(String w, int c) {
            word = w;
            count = c;
        }
    }
    
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            int count = map.getOrDefault(w, 0);
            map.put(w, ++count);
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.count == o2.count) {
                return o1.word.compareTo(o2.word);
            }
            
            return o2.count - o1.count;
        });
        
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
            Pair pair = new Pair(e.getKey(), e.getValue());
            pq.add(pair);
        }
        
        List<String> res = new ArrayList<>();
        
        while (k > 0) {
            res.add(pq.poll().word);
            k--;
        }
        
        return res;
    }

    /**
     * 优化版本 6ms 不需要新建Pair class
     * 直接用map.entry放到PQ里就行
     * PQ.poll时间复杂度O(logn)
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent_opt(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            int count = map.getOrDefault(w, 0);
            map.put(w, ++count);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.getValue() == o2.getValue()) {
                return o1.getKey().compareTo(o2.getKey());
            }
            
            return o2.getValue() - o1.getValue();
        });
        
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            pq.add(e);
        }
        
        List<String> res = new ArrayList<>();
        
        while (k > 0) {
            res.add(pq.poll().getKey());
            k--;
        }
        
        return res;
    }
}