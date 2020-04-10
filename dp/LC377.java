public class LC377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int j = 0; j <= target; j++) {
            for (int num : nums) {
                if (num <= j) {
                    dp[j] += dp[j - num];
                }
            }
        }
        
        return dp[target];
    }
}