
public class LC1049 {
    /**
     * 问题转化: 把数组分成两部分，求两部分的最小差值
     * S = S1 + S2, diff = S2 - S1 -> diff = S - 2S2 所以就是要找出数组中能构成小于等于1/2S的最大值
     * dp[i][j]=true: 长度为i的数组可以构成的和为j
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0, n = stones.length;
        for (int stone : stones) sum += stone;
        
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum];
        int max = 0;
        
        dp[0][0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i-1][j] || (j - stones[i-1] >= 0 && dp[i-1][j - stones[i-1]]);
                if (dp[i][j]) max = Math.max(max, j);
            }
        }
        
        return sum - 2 * max;
    }
}