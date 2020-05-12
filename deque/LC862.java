import java.util.*;
class LC862 {
    /**
     * deque中维护递增栈
     * A = [3,-1,2,5,6] K = 7
     * presum [0,3,2,4,9,15]
     *                 |
     * 当i=9的时候循环去队列头部找满足sum[i]-sum[peekFirst]>=K的元素 
     */
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] sums = new int[n + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int res = n + 1;
        
        for (int i = 1; i <= n; i++) sums[i] = sums[i - 1] + A[i - 1];
        
        for (int i = 0; i < n + 1; i++) {
            while (!dq.isEmpty() && sums[i] - sums[dq.peekFirst()] >= K) {
                //一直找到长度最短的subarray满足sum>=k
                res = Math.min(res, i - dq.pollFirst());
            }
                
            while (!dq.isEmpty() && sums[dq.peekLast()] >= sums[i]) {
                dq.pollLast();
            }
            
            dq.offerLast(i);
        }
        
        
        return res <= n ? res : -1;
    }
}