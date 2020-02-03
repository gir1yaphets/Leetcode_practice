import java.util.*;
class LC253 {
    /**
     * pq小顶堆记录结束时间
     * 每次来一个interval都判断一下它的起始时间是否大于当前最小的结束时间，如果大于则把最早结束的时间从pq里poll掉
     * 表示当前会议可以与最小结束时间的会议公用一个会议室
     * TC O(nlogn)
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());

        int res = 0;
        pq.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            
            pq.offer(intervals[i][1]);
        }
        
        return pq.size();
    }
}