package backtrack;
import java.util.*;

class LC494 {
    private int count = 0;
    
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, 0, S, 0);
        
        return count;
    }
    
    private void dfs(int[] nums, int start, int S, int sum) {
        if (start == nums.length) {
            if (sum == S) {
                count += 1;
            }
        } else {
            dfs(nums, start + 1, S, sum + nums[start]);
            
            dfs(nums, start + 1, S, sum - nums[start]);
        }
    }

    /**
     * 用注释掉的两行做循环[1], 1过不了
     * 有空需要再研究一下
     */
    public int findTargetSumWays_dp(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        
        if (sum < S) return 0;
        
        int n = nums.length;
        int[][] dp = new int[n+1][2 * sum + 1];
        
        dp[0][sum] = 1;
        
        for (int i = 1; i <= n; i++) {
            //for (int j = nums[i-1]; j + nums[i-1] < 2 * sum + 1; j++)
            for (int j = 0; j < 2 * sum + 1; j++) {
                if (j + nums[i-1] < 2 * sum + 1) dp[i][j] += dp[i-1][j+nums[i-1]];
                if (j - nums[i-1] >= 0) dp[i][j] += dp[i-1][j-nums[i-1]];
                // dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j+nums[i-1]];
            }
        }
        
        return dp[nums.length][S + sum];
    }
}