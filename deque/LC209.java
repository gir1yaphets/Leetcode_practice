import java.util.*;
public class LC209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n  = nums.length;
        int[] sums = new int[n + 1];
        int res = n + 1;
        
        for (int i = 1; i <= n; i++) sums[i] = sums[i-1] + nums[i-1];
        
        Deque<Integer> q = new ArrayDeque<>();
        
        for (int i = 0; i <= n; i++) {
            while (!q.isEmpty() && sums[i] - sums[q.peekFirst()] >= s) {
                res = Math.min(res, i - q.pollFirst());
            }
            
            while (!q.isEmpty() && sums[i] <= sums[q.peekLast()]) {
                q.pollLast();
            }
            
            q.offerLast(i);
        }
        
        return res > n ? 0 : res;
    }

    public int minSubArrayLen_sliding_window(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n  = nums.length;
        int sum = 0;
        int res = n + 1;
        
        int l = 0, r = 0;
        
        while (r < n) {
            sum += nums[r++];
            
            while (sum >= s) {
                res = Math.min(res, r - l);
                sum -= nums[l++];
            }
        }
        
        return res > n ? 0 : res;
    }

    public int minSubArrayLen_binarySearch(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n  = nums.length;
        int[] sums = new int[n + 1];
        int res = n + 1;
        
        for (int i = 1; i <= n; i++) sums[i] = sums[i-1] + nums[i-1];
        
        for (int i = 1; i <= n; i++) {
            int index = binarySearch(sums, 0, i, sums[i], s);
            if (index == -1) continue;
            res = Math.min(i - index, res);
        }
        
        return res > n ? 0 : res;
    }
    
    private int binarySearch(int[] sums, int l, int r, int sum, int s) {
        while (l < r) {
            int m = l + (r - l) / 2 + 1;
            
            if (sum - sums[m] < s) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        
        return sum - sums[l] >= s ? l : -1;
    }
}