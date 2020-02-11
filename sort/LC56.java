import java.util.*;
class LC56 {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int[] in : intervals) {
            pq.offer(in);
        }
        
        List<int[]> list = new ArrayList<>();
        
        while (!pq.isEmpty()) {
            int[] d = pq.poll();
            
            if (list.size() == 0 || list.get(list.size() - 1)[1] < d[0]) {
                list.add(d);
            } else {
                int[] last = list.get(list.size() - 1);
                //注意下一个区间结尾比当前区间结尾小的情况
                last[1] = Math.max(d[1], last[1]);
            }
        }
        
        return list.toArray(new int[0][0]);
    }
}