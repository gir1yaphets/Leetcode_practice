import java.util.*;
class LC1167 {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        for (int len : sticks) {
            pq.offer(len);
        }
        
        int sum = 0;
        
        while (pq.size() > 1) {
            int cost = pq.poll() + pq.poll();
            sum += cost;
            
            //之前错就因为少这个操作，如果两个stick相加的和已经不是最小的了，那么下次就不能用这个继续相加，所以要重新将其放回pq
            pq.offer(cost);
        }
        
        return sum;
    }
}