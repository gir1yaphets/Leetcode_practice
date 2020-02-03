import java.util.*;
class LC347 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.add(e);
            
            //当pq中元素 >k个的时候将堆顶元素poll掉 保证pq size = k, 这样pq的时间复杂度为logk 而不是logn
            //注意这种做法需要用小顶堆
            if (pq.size() > k) {
                pq.poll();
            }
        }
        
        List<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }
        
        return res;
    }
}