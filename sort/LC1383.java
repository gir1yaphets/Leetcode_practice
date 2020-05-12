import java.util.*;
class LC1383 {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int mod = 1000000007;
        int[][] eng = new int[n][2];
        for (int i = 0; i < n; i++) {
            eng[i][0] = efficiency[i];
            eng[i][1] = speed[i];
        }
        
        //先对整个数组按照efficiency descending排序，再对speed ascending排序
        //用pq对k个人进行排序，因为eng数组前面的都是efficiency高的，当超过k的时候，每次再踢掉一个speed少的，找下一个efficiency少的，看能不能超过当前的总效率
        //就比如先找效率高的，但是效率高的可能干活时间短(speed小)，然后在当前这里踢出一个干活时间短的人，找一个效率没有他高但是比他干活时间长的人，看团队总效率能否超过之前
        Arrays.sort(eng, (a, b) -> b[0] - a[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            pq.offer(eng[i][1]);
            sum += eng[i][1];
        
            if (pq.size() > k) {
                sum -= pq.poll();
            }
            
            max = Math.max(max, eng[i][0] * sum);
        }
        
        return (int) (max % mod);
    }
}