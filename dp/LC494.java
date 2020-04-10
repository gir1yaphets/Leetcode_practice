
public class LC494 {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        int n = nums.length;
        
        if (sum < S) return 0;
        
        //dp[i][j]用前i个元素能构成和为j的个数
        int[][] dp = new int[n+1][2*sum+1];
        
        //注意这里不是dp[0][0]=1,正常是以0个元素构成和为0应该是1，但是这里有sum个偏移，所以是dp[0][sum] = 1
        dp[0][sum] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                if (j + nums[i-1] < 2 * sum + 1) dp[i][j] += dp[i-1][j+nums[i-1]];
                if (j - nums[i-1] >= 0) dp[i][j] += dp[i-1][j-nums[i-1]];
            }
        }
        
        return dp[n][S+sum];
    }

    /**
     * brute force
     */
    private int count = 0;
    
    public int findTargetSumWays_bf(int[] nums, int S) {
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
}