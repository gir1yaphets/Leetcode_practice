import java.util.*;
class LC1425 {
    /**
     * dp[i]表示到达i这一点的最大和,当前点必须要取
     * 因为dp[i]可以从dp[j]转移过来，但这个必须是取到dp[j]的最大值，而不能是dp[j]是没有取到j的前面的最大值，这样i和j之间就可能超过k
     * 可以理解为跳台阶，第i个台阶是踩着台阶j跳过来的，所以这样也决定了最大值不一定出现在dp[n-1]
     * dp[i] = Math.max(dp[j], 0) + nums[i] (i-j<=k)
     * Time O(n * k) TLE
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i] = Math.max(Math.max(dp[i-j], 0) + nums[i], dp[i]);
            }
        
            //最大值不一定出现在最后一个点
            max = Math.max(dp[i], max);
        }
        
        return max;
    }

    public int constrainedSubsetSum_mono_q(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        
        Deque<Integer> q = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            //当前sliding window的个数已经超过k个了，并且最大值是q的最前端的那个值，这个值就不能再用了，将其poll掉
            if (!q.isEmpty() && q.peekFirst() < i - k) {
                q.pollFirst();
            }

            //dp[i]仍然表示nums[i]必须取能获得的sum的最大值，它等于q中的最大值(第一个) + nums[i]
            dp[i] = (q.isEmpty() ? 0 : Math.max(dp[q.peekFirst()], 0)) + nums[i];
            
            //维护单调递减队列，小于dp[i]的值在[i-k,i]都不可能成为最大值，从q中移除
            while (!q.isEmpty() && dp[i] > dp[q.peekLast()]) {
                q.pollLast();
            }
            
            q.offerLast(i);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}