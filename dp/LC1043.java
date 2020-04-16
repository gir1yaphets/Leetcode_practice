
public class LC1043 {
    /**
     * dp[i]:前i个元素分组后构成的最大值
     * dp[i] = max(dp[i], dp[i-k] + k * max([i-k, i]))
     */
    public int maxSumAfterPartitioning(int[] A, int K) {
        int n = A.length;
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= Math.min(i, K); j++) {
                max = Math.max(max, A[i - j]);
                dp[i] = Math.max(dp[i], dp[i-j] + max * j);
            }
        }
        
        return dp[n];
    }
}